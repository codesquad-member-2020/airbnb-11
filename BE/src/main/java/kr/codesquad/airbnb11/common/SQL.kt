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
     , ST_X(r.location) AS longitude
     , ST_Y(r.location) AS latitude
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
const val SELECT_PRICES_WITH_SEARCH_PARAMS: String = """
SELECT r.daily_price
  FROM room       r
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
CALL SEARCH_NEAR_ROOM(:longitude, :latitude, :checkIn, :checkOut, :minPersonCount, :priceMin, :priceMax);
"""
