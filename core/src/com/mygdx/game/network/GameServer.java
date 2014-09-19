package com.mygdx.game.network;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.GameSettings;
import com.mygdx.game.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administratör on 2014-09-19.
 */
public class GameServer {

    public static void main(String[] args) throws IOException {
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



                if(o instanceof HitRequest){
                    HitRequest request = (HitRequest) o;
                    Player p  = request.getPlayer();


                    HitResponse r = new HitResponse();
                    r.setPlayer(p);
                    //connection.sendTCP(r);
                    server.sendToAllTCP(r);
                }
                if( o instanceof AddPlayerRequest){
                    AddPlayerRequest addPlayerRequest = (AddPlayerRequest) o;



                    Player p = new Player(players.size()+1);
                    players.add(p);
                    //ADD PLAYER IF NEEDED
                    if(players.size() == 0){
                        leader = p;
                    }
                    AddPlayerResponse pr = new AddPlayerResponse();
                    pr.setText("Added player " + p.getPlayerNumber());
                    pr.setPlayer(p);
                    connection.sendTCP(pr);

                }

                if(o instanceof StartGameRequest){

                    StartGameRequest sgr = (StartGameRequest) o;
                    if(sgr.getPlayer().equals(leader)) {
                        StartGameResponse response = new StartGameResponse();

                        response.setPlayers(players);
                        response.setSettings(gameSettings);
                        server.sendToAllTCP(response);
                    }
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
        server.bind(Network.TCPPORT,Network.UDPPORT);
        server.start();



    }

    private void checkConnectedPlayers() {
        for (Player p : players){

        }
    }


}

