package kr.codesquad.airbnb11.domain.room;

import static kr.codesquad.airbnb11.common.SQLKt.COUNT_ROOMS_WITH_SEARCH_PARAMS;
import static kr.codesquad.airbnb11.common.SQLKt.SELECT_ROOMS_WITH_SEARCH_PARAMS;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import kr.codesquad.airbnb11.controller.request.SearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDAO {

  private static final Logger log = LoggerFactory.getLogger(RoomDAO.class);

  private final NamedParameterJdbcTemplate jdbcTemplate;

  public RoomDAO(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  public List<Room> selectRoomsWithSearchParams(SearchRequest searchRequest) {
    SqlParameterSource parameters = new MapSqlParameterSource(searchRequest.getParameterMap());
    log.debug("parameters: {}", parameters);

    return jdbcTemplate.query(SELECT_ROOMS_WITH_SEARCH_PARAMS, parameters, (rs, rowNum) ->
        Room.builder()
            .id(rs.getInt("id"))
            .maxPersonCount(rs.getInt("max_person_count"))
            .mainImage(rs.getString("main_image"))
            .title(rs.getString("title"))
            .description(rs.getString("description"))
            .dailyPrice(new BigDecimal(rs.getString("daily_price")))
            .country(rs.getString("country"))
            .build()
    );
  }

  public Integer countRoomsWithSearchParams(SearchRequest searchRequest) {
    SqlParameterSource parameters = new MapSqlParameterSource(searchRequest.getParameterMap());
    log.debug("parameters: {}", parameters);

    return jdbcTemplate.queryForObject(COUNT_ROOMS_WITH_SEARCH_PARAMS, parameters, Integer.class);
  }
}
