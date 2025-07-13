package DBConnection;
import utils.ConfigUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    // Define the database connection details
    private static final String DB_URL = "";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private Object merchantID;

    // Method to get a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to close database resources
    private void closeResources(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute a query and retrieve data as a list of strings
    public List<String> fetchDataFromDatabase(String query, String column) throws SQLException {
        List<String> dataFromDB = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String value = resultSet.getString(column);
                dataFromDB.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataFromDB;
    }

    // Method to execute a query and retrieve data as a list of maps
    public List<Map<String, Object>> fetchAllDataFromDatabase(String query) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    rowData.put(columnName, columnValue);
                }
                result.add(rowData);
            }
        }
        return result;
    }

    // Common method to fetch merchant details based on email
    public List<Map<String, Object>> fetchMerchantDetailsFromDatabase() throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.merchant where Email=\""+ ConfigUtils.getInstance().getMerchantEmail()+"\"";
        return fetchAllDataFromDatabase(query);
    }

    //***get merchant total orders***//
    public List<Map<String, Object>> fetchMerchantOrdersFromDatabase() throws SQLException {
        merchantID = fetchMerchantDetailsFromDatabase().get(0).get("ID");
        String query = "SELECT * FROM bnpl_staging_new.orders where MerchantID="+merchantID.toString();
        return fetchAllDataFromDatabase(query);
    }

    //***get merchant wallet***//
    public List<Map<String, Object>> fetchMerchantWalletFromDatabase() throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.wallet WHERE ID=(SELECT WalletID FROM bnpl_staging_new.merchant_wallet where MerchantID="+merchantID+")";
        return fetchAllDataFromDatabase(query);
    }

    // Common method for searching invoices in the database
    private List<String> searchInvoicesInDatabase(String searchField, int orderStatus) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND ReferenceNumber LIKE '%" + searchField + "%' AND OrderStatus = " + orderStatus + " ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant invoices and search in it***//
    public List<String> fetchMerchantInvoicesAndSearchInItFromDatabase(String searchField) throws SQLException {
        return searchInvoicesInDatabase(searchField, 125);
    }

    //***get merchant invoices and filter by date***//
    public List<String> fetchMerchantInvoicessAndFilterItFromDatabase(String startDate, String endDate) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND DATE(CreatedAt) BETWEEN '" + startDate + "' AND '" + endDate
                + "' AND OrderStatus = 125 ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant orders and filter by date***//
    public List<String> fetchMerchantOrdersAndFilterItFromDatabase(String startDate, String endDate) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND DATE(CreatedAt) BETWEEN '" + startDate + "' AND '" + endDate
                + "' ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant completed orders***//
    public List<String> fetchMerchantOrdersCompletedFromDatabase() throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND OrderStatus = 125 ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant incompleted orders***//
    public List<String> fetchMerchantOrdersInCompletedFromDatabase() throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND OrderStatus NOT IN (125, 135) ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    // Common method to fetch the sum of amounts for completed or incompleted orders
    private double fetchMerchantOrderAmountFromDatabase(int orderStatus) throws SQLException {
        String query = "SELECT SUM(Amount) FROM bnpl_staging_new.orders WHERE MerchantID = ? AND OrderStatus = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(merchantID.toString()));
            preparedStatement.setInt(2, orderStatus);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble(1);
                } else {
                    throw new SQLException("No results found for the merchant.");
                }
            }
        }
    }

    //***get merchant completed orders' total amount***//
    public double fetchMerchantOrdersCompletedAmountFromDatabase() throws SQLException {
        return fetchMerchantOrderAmountFromDatabase(125);
    }

    //***get merchant incompleted orders' total amount***//
    public double fetchMerchantOrdersInCompletedAmountFromDatabase() throws SQLException {
        return fetchMerchantOrderAmountFromDatabase(135);
    }

    // Common method for searching orders in the database
    private List<String> searchOrdersInDatabase(String searchField) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.orders WHERE MerchantID = " + merchantID
                + " AND ReferenceNumber LIKE '%" + searchField + "%' ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant orders and search in it***//
    public List<String> fetchMerchantOrdersAndSearchInItFromDatabase(String searchField) throws SQLException {
        return searchOrdersInDatabase(searchField);
    }

    // Common method for searching transactions in the database
    private List<String> searchTransactionsInDatabase(String searchField) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.transactions WHERE ToMerchantID = " + merchantID
                + " AND ReferenceNumber LIKE '%" + searchField + "%' AND Direction=1 ORDER BY ReferenceNumber DESC";
        return fetchDataFromDatabase(query, "ReferenceNumber");
    }

    //***get merchant transactions and search in it***//
    public List<String> fetchMerchantTransactionsAndSearchInItFromDatabase(String searchField) throws SQLException {
        return searchTransactionsInDatabase(searchField);
    }

    //***get merchant brands***//
    public List<String> fetchMerchantBrandsFromDatabase() throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.Brands WHERE MerchantID = " + merchantID;
        return fetchDataFromDatabase(query, "NameEn");
    }

    //***get merchant brand Id by name***//
    public List<String> fetchMerchantBrandIdFromDatabase(String brandName) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.Brands WHERE NameEn = '" + brandName + "'";
        return fetchDataFromDatabase(query, "Id");
    }

    //***get merchant branch Id by name***//
    public List<String> fetchMerchantBranchIdFromDatabase(String branchName) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.merchant_stores WHERE Name = '" + branchName + "'";
        return fetchDataFromDatabase(query, "Id");
    }

    //***get merchant branches for a brand***//
    public List<String> fetchMerchantBranchesForBrandFromDatabase(String brandId) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.merchant_stores WHERE BrandId = " + brandId;
        return fetchDataFromDatabase(query, "Name");
    }

    //***get merchant brand data***//
    public List<Map<String, Object>> fetchMerchantBrandDataFromDatabase(String brandId) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.Brands WHERE Id = " + brandId;
        return fetchAllDataFromDatabase(query);
    }

    //***get merchant branch data***//
    public List<Map<String, Object>> fetchMerchantBranchDataFromDatabase(String branchId) throws SQLException {
        String query = "SELECT * FROM bnpl_staging_new.merchant_stores WHERE Id = " + branchId;
        return fetchAllDataFromDatabase(query);
    }
}
