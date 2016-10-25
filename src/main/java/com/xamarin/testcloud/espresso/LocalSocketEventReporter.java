package com.xamarin.testcloud.espresso;

import android.net.LocalServerSocket;

import com.xamarin.testcloud.event.Event;
import com.xamarin.testcloud.event.EventReporter;

import java.io.IOException;

public class LocalSocketEventReporter extends EventReporter {
    private final EventServer server;

    public LocalSocketEventReporter(String socketName) {
        EventServer server1;
        try {
            LocalServerSocket socket = new LocalServerSocket(socketName);
            server1 = new EventServer(socket);
        } catch (IOException e) {
            server1 = null;
            new RuntimeException("Unable to create server socket");
        }
        this.server = server1;
    }

    @Override
    protected void commit(Event event) throws IOException {
        boolean b = server.sendSync(event);
    }
}
