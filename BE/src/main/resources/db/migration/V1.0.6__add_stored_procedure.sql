DELIMITER //
CREATE PROCEDURE SEARCH_NEAR_ROOM(longitude DECIMAL(17, 14), latitude DECIMAL(16, 14),
                                  check_in DATE,
                                  check_out DATE, min_person_count INT, price_min DECIMAL,
                                  price_max DECIMAL)
BEGIN
    SET @lon = longitude;
    SET @lat = latitude;

# 검색 범위 단위 m(미터)
    SET @mbr_length = 20000;

# 내 위치에서 동쪽 또는 서쪽으로 2.5km (MBR 한 변의 길이의 절반) 떨어지기 위해 필요한 경도의 차이값
    SET @lon_diff = @mbr_length / 2 /
                    ST_DISTANCE_SPHERE(POINT(@lon, @lat), POINT(@lon + IF(@lon < 0, 1, -1), @lat));

# 내 위치에서 남쪽 또는 북쪽으로 2.5km 떨어지기 위해 필요한 위도의 차이값
    SET @lat_diff = @mbr_length / 2 /
                    ST_DISTANCE_SPHERE(POINT(@lon, @lat), POINT(@lon, @lat + IF(@lat < 0, 1, -1)));

# MBR의 대각선 = LINESTRING(좌표1, 좌표2)
    SET @diagonal = CONCAT('LINESTRING(', @lon - IF(@lon < 0, 1, -1) * @lon_diff, ' ',
                           @lat - IF(@lon < 0, 1, -1) * @lat_diff, ',',
                           @lon + IF(@lon < 0, 1, -1) * @lon_diff, ' ',
                           @lat + IF(@lon < 0, 1, -1) * @lat_diff, ')');

    SELECT r.id
         , r.max_person_count
         , r.main_image
         , r.title
         , r.description
         , r.daily_price
         , r.cleaning_price
         , r.service_price
         , r.commission
         , r.country
         , r.user_id
         , r.rating
         , r.review_count
         , IF(u.is_super_host, 'TRUE', 'FALSE')            AS is_super_host
         , ST_X(r.location)                                AS longitude
         , ST_Y(r.location)                                AS latitude
         , ST_DISTANCE_SPHERE(POINT(@lon, @lat), location) AS distance
    FROM room r FORCE INDEX FOR JOIN (`location_spatial_index`)
             INNER JOIN user u ON r.user_id = u.id
    WHERE MBRContains(ST_LINESTRINGFROMTEXT(@diagonal), location)
      AND r.id NOT IN (
        SELECT DISTINCT rev.room_id
        FROM reservation rev
        WHERE rev.reservation_date
                  BETWEEN IFNULL(DATE(check_in), NOW())
                  AND IFNULL(DATE(check_out), DATE('2020-12-30'))
    )
      AND r.max_person_count >= IFNULL(min_person_count, 0)
      AND r.daily_price BETWEEN IFNULL(price_min, 0) AND IFNULL(price_max, 1000000)
    LIMIT 50;
END //

DELIMITER ;
