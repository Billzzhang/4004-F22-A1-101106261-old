package project;

import java.io.Console;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class is to be connected to GameServer as a child thread and creates ServerInfoServers
 * for testCases to communicate with GameServer is to act as a mediator
 *
 */

public class ServerInfo implements Runnable{

    @Override
    public void run() {

    }

    /**
     * ServerInfoServer Object that is to be connected to GameServer as a child thread and is to act as a mediator for the TestCase and GameServer
     *
     */
    public class ServerInfoServer implements Runnable {

        public void run() {

        }
    }


}
