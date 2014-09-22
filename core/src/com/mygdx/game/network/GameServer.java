package com.mygdx.game.network;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.CountDown;
import com.mygdx.game.GameSettings;
import com.mygdx.game.model.Center;
import com.mygdx.game.model.Player;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2014-09-19.
 */
public class GameServer {


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("SERVER");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(100,100);
        frame.setVisible(true);
        new GameServer();

    }


    private List<Player> players;
    private GameSettings gameSettings;
    private Player leader;

    public GameServer() throws IOException {
        final Server server = new Server();
        players = new ArrayList<Player>();
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {


                if (o instanceof HitRequest) {
                    HitRequest request = (HitRequest) o;
                    Player p = request.getPlayer();


                    HitResponse r = new HitResponse();
                    r.setPlayer(p);
                    //connection.sendTCP(r);
                    server.sendToAllTCP(r);
                }
                if (o instanceof AddPlayerRequest) {
                    AddPlayerRequest addPlayerRequest = (AddPlayerRequest) o;


                    Player p = new Player(players.size() + 1);
                    //ADD PLAYER IF NEEDED
                    if (players.size() == 0) {
                        leader = p;
                    }

                    players.add(p);
                    AddPlayerResponse pr = new AddPlayerResponse();
                    pr.setText("Added player " + p.getPlayerNumber());
                    pr.setPlayer(p);
                    connection.sendTCP(pr);

                }

                if (o instanceof StartGameRequest) {

                    StartGameRequest sgr = (StartGameRequest) o;
                    //if(sgr.getPlayer().equals(leader)) {
                    StartGameResponse response = new StartGameResponse();

                    gameSettings = new GameSettings();
                    gameSettings.setNumberOfLouies(2);
                    gameSettings.setNumberOfChickens(5);
                    response.setPlayers(players);
                    response.setSettings(gameSettings);


                    server.sendToAllTCP(response);
                    //  }
                }


            }

            @Override
            public void connected(Connection connection) {
                //   Client c = (Client) connection.getEndPoint();
                System.out.println("CONNECTED");


            }

            @Override
            public void disconnected(Connection connection) {
                checkConnectedPlayers();
            }
        });
        server.bind(Network.TCPPORT, Network.UDPPORT);
        server.start();


    }

    private void checkConnectedPlayers() {
        for (Player p : players) {

        }
    }


    public void sendFrameUpdate(Center center, List<Player> players, CountDown countDown) {

    }
}

