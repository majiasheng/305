package com.ajax.persistence;

import com.ajax.model.Constants;
import com.ajax.model.Auction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class AuctionDAOImpl implements AuctionDAO {

    @Override
    public boolean saveAuction(Auction auction) {
        Connection conn = MySQLConnection.connect();
        try {
            String query = "INSERT INTO "
                    + Constants.AUCTIONS_TABLE
                    + " ("
                    + Constants.ACCOUNTNO_FIELD + ", "
                    + Constants.AIRLINEID_FIELD + ", "
                    + Constants.FLIGHTNO_FIELD + ", "
                    + Constants.CLASS_FIELD + ", "
                    + Constants.NYOP_FIELD
                    + ") VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, auction.getPersonAccNo());
            stmt.setString(2, auction.getAirline());
            stmt.setInt(3, auction.getFlightNo());
            stmt.setString(4, auction.getFlightClass().name());
            stmt.setDouble(5, auction.getNYOP());

            stmt.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error occurred while trying to insert record into auctions table.");
            return false;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;

    }

    @Override
    public List<Auction> getAllAuctionHistory(int customerAccNo) {
        List<Auction> auctions = null;
        Connection conn = MySQLConnection.connect();
        try {
            String query = "SELECT A.* , IF(A.NYOP >= F.Fare, 'Yes', 'No') AS 'Accepted?' "
                    + "FROM auctions A, fare F "
                    + "WHERE F.FareType='Hidden' "
                    + "AND A.AirlineID=F.AirlineID "
                    + "AND A.FlightNo=F.FlightNo "
                    + "AND A.Class=F.Class "
                    + "AND " + Constants.ACCOUNTNO_FIELD + " = ? ";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerAccNo);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            auctions = new ArrayList<>();
            while (rs.next()) {
                String airlineId = rs.getString(Constants.AIRLINEID_FIELD);
                int flightNo = rs.getInt(Constants.FLIGHTNO_FIELD);
                String flightClass = rs.getString(Constants.CLASS_FIELD);
                Timestamp timestamp = rs.getTimestamp(Constants.DATE_FIELD);
                double NYOP = rs.getDouble(Constants.NYOP_FIELD);
                String accepted = rs.getString("Accepted?");

                Auction auction = new Auction(customerAccNo, NYOP, airlineId, flightNo, flightClass, timestamp, accepted.equals("Yes"));
                auctions.add(auction);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("\n\nauctions: " + auctions.size());
        
        return auctions;
    }

    public List<Auction> getAuctionHistoryByFlight(int customerAccNo, String airline, int flightNo, String flightClass) {
        List<Auction> auctions = null;

        Connection conn = MySQLConnection.connect();
        try {
            String query = "SELECT A.NYOP,"
                    + "IF(A.NYOP >= F.Fare, 'Yes', 'No') AS Accepted?,"
                    + " A.Date"
                    + "FROM " + Constants.AUCTIONS_TABLE + " A, " + Constants.FARE_TABLE + " F "
                    + "WHERE A." + Constants.ACCOUNTNO_FIELD + " = ? "
                    + "AND F." + Constants.FARE_TYPE_FIELD + " = 'Hidden'"
                    + "AND A." + Constants.AIRLINEID_FIELD + " = ? "
                    + "AND A." + Constants.FLIGHTNO_FIELD + " = ? "
                    + "AND A." + Constants.CLASS_FIELD + " = ? "
                    + "AND F." + Constants.AIRLINEID_FIELD + " = A." + Constants.AIRLINEID_FIELD + "  "
                    + "AND F." + Constants.FLIGHTNO_FIELD + " = A." + Constants.FLIGHTNO_FIELD + " "
                    + "AND F." + Constants.CLASS_FIELD + " = A." + Constants.CLASS_FIELD + ";"
                    + "";
            PreparedStatement stmt = conn.prepareStatement(query);

            //TODO: set params
            stmt.setInt(1, customerAccNo);
            stmt.setString(2, airline);
            stmt.setInt(3, flightNo);
            //TODO: need to add flight class to auction ? or retrieve it from auction
            // stmt.setString(4, flightClass);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            auctions = new ArrayList<>();
            //TODO: get auction history given airline+airline

        } catch (SQLException ex) {
            Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return auctions;
    }

}
