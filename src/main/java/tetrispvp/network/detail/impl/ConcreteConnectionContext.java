package tetrispvp.network.detail.impl;

import tetrispvp.network.ConnectionContext;
import tetrispvp.network.detail.Connection;
import tetrispvp.network.detail.ConnectionFactory;
import tetrispvp.network.detail.LocalEndpoint;

import java.util.UUID;

public class ConcreteConnectionContext implements ConnectionContext, LocalEndpoint {

    private Connection connection;
    private String uid = UUID.randomUUID().toString();
    private String toAddress = null;

    @Override
    public synchronized void connect(String address) {
        if (connection != null)
            connection.close();

        toAddress = address;
        getConnection();
    }

    @Override
    public synchronized void disconnect() {
        toAddress = null;
        connection.close();
    }

    @Override
    public synchronized String thisAddress() {
        if (connection == null)
            return "";
        return connection.thisAddress();
    }

    @Override
    public String getUID() {
        return uid;
    }

    public synchronized Connection getConnection() {
        if (connection == null || !connection.isOpen()) {
            if (toAddress != null)
                connection = ConnectionFactory.connect(this, toAddress);
            else
                connection = ConnectionFactory.listen(this);
        }

        return connection;
    }

    public synchronized void cleanUp() {
        if (connection != null) {
            if (connection.isOpen())
                connection.close();
            connection = null;
        }
    }
}
