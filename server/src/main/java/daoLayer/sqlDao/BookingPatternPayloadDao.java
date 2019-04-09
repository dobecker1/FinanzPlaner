package daoLayer.sqlDao;

import factory.ModelFactory;
import models.patternBooking.interfaces.BookingPatternPayload;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class BookingPatternPayloadDao extends BasicDao {

    private static final String BOOKING_PAYLOAD_TABLE = "BOOKING_PAYLOAD";

    public int write(BookingPatternPayload payload) {
        int mapId = this.writeMapPayload(payload.getBookingPatternPayload());
        int patternPayloadId = -1;
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO BOOKING_PAYLOAD(MAPID) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, mapId);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                patternPayloadId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patternPayloadId;
    }

    public BookingPatternPayload read(int id) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING_PAYLOAD WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                BookingPatternPayload patternPayload = ModelFactory.getBookingPatternPayload();
                int mapId = result.getInt("mapId");
                patternPayload.setBookingPatternPayload(this.findMapById(mapId));
                patternPayload.setId(result.getInt("id"));
                return patternPayload;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public void delete(BookingPatternPayload payload) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM BOOKING_PAYLOAD WHERE id = ?");
            statement.setInt(1, payload.getId());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                int mapId = result.getInt("mapId");
                this.deleteMap(mapId);
                super.delete(payload, BOOKING_PAYLOAD_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler");
        }
    }

    public int writeMapPayload(Map<String, String> payloadEntries) {
        int mapId = 0;
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("INSERT INTO MAP(TYPE) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, "MAP");
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                mapId = generatedKeys.getInt(1);
            }
            generatedKeys.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(mapId > 0) {
            PreparedStatement statement;
            for (Map.Entry<String, String> entry : payloadEntries.entrySet()) {
                try {
                    statement = super.controller.connection.
                            prepareStatement("INSERT INTO MAPENTRY(MAPID, MAPKEY, MAPVALUE) VALUES(?,?,?)");
                    statement.setInt(1, mapId);
                    statement.setString(2, entry.getKey());
                    statement.setString(3, entry.getValue());
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return mapId;
    }

    public Map<String, String> findMapById(int mapId) {
        Map<String, String> map = new HashMap<>();

        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("SELECT * FROM MAPENTRY WHERE MAPID = ?");
            statement.setInt(1, mapId);
            ResultSet  result = statement.executeQuery();
            while(result.next()) {
                map.put(result.getString("mapKey"), result.getString("mapValue"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void deleteMap(int mapId) {
        try {
            PreparedStatement statement = super.controller.connection.
                    prepareStatement("DELETE FROM MAPENTRY WHERE MAPID = ?");
            statement.setInt(1, mapId);
            statement.executeUpdate();
            statement = super.controller.connection.
                    prepareStatement("DELETE FROM MAP WHERE ID = ?");
            statement.setInt(1, mapId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
