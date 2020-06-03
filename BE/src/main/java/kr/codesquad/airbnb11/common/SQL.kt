package kr.codesquad.airbnb11.common

// 한 요청에 보여줄 data 개수
const val OFFSET_COUNT: Int = 20

// language=SQL
const val SELECT_ROOMS_WITH_SEARCH_PARAMS: String = """
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
     , IF(u.is_super_host, 'TRUE', 'FALSE') AS is_super_host
     , X(r.location) AS longitude
     , Y(r.location) AS latitude
  FROM room       r
  INNER JOIN user u ON r.user_id = u.id
 WHERE r.id NOT IN (
     SELECT DISTINCT rev.room_id
       FROM reservation rev
      WHERE rev.reservation_date
                BETWEEN IFNULL(DATE(:checkIn), NOW())
                AND IFNULL(DATE(:checkOut), DATE('2020-12-30'))
 )
   AND r.max_person_count >= IFNULL(:minPersonCount, 0)
   AND r.daily_price BETWEEN IFNULL(:priceMin, 0) AND IFNULL(:priceMax, 1000000)
 LIMIT :offset, ${OFFSET_COUNT};
"""

// language=SQL
const val COUNT_ROOMS_WITH_SEARCH_PARAMS: String = """
SELECT COUNT(*)
  FROM room       r
  INNER JOIN user u ON r.user_id = u.id
 WHERE r.id NOT IN (
     SELECT DISTINCT rev.room_id
       FROM reservation rev
      WHERE rev.reservation_date
                BETWEEN IFNULL(DATE(:checkIn), NOW())
                AND IFNULL(DATE(:checkOut), DATE('2020-12-30'))
 )
   AND r.max_person_count >= IFNULL(:minPersonCount, 0)
   AND r.daily_price BETWEEN IFNULL(:priceMin, 0) AND IFNULL(:priceMax, 1000000)
"""

// language=SQL
const val SELECT_ROOM_DETAIL_BY_ID: String = """
SELECT r.id
     , r.max_person_count
     , r.title
     , r.description
     , r.daily_price
     , r.cleaning_price
     , r.service_price
     , r.commission
     , r.country
     , r.rating
     , r.review_count
     , IF(u.is_super_host, 'TRUE', 'FALSE') AS is_super_host
     , X(r.location) AS longitude
     , Y(r.location) AS latitude
  FROM room       r
  INNER JOIN user u ON r.user_id = u.id
 WHERE r.id = :id
"""

// language=SQL
const val SELECT_NEAR_ROOM: String = """
# 검색할 중심 경도, 위도
SET @lon = :longitude;
SET @lat = :latitude;

# 검색 범위 단위 m(미터)
SET @mbr_length = 5000;

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
     , X(r.location)                                   AS longitude
     , Y(r.location)                                   AS latitude
     , ST_DISTANCE_SPHERE(POINT(@lon, @lat), location) AS distance
  FROM room       r FORCE INDEX FOR JOIN (`location_spatial_index`)
  INNER JOIN user u ON r.user_id = u.id
 WHERE MBRContains(ST_LINESTRINGFROMTEXT(@diagonal), location)
   AND r.id NOT IN (
     SELECT DISTINCT rev.room_id
       FROM reservation rev
      WHERE rev.reservation_date
                BETWEEN IFNULL(DATE(:checkIn), NOW())
                AND IFNULL(DATE(:checkOut), DATE('2020-12-30'))
 )
   AND r.max_person_count >= IFNULL(:minPersonCount, 0)
   AND r.daily_price BETWEEN IFNULL(:priceMin, 0) AND IFNULL(:priceMax, 1000000)
 LIMIT 10;
"""
