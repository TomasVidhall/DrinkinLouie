package com.mygdx.game.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.*;
import com.mygdx.game.model.Player;

import java.io.IOException;

/**
 * Created by Administratör on 2014-09-19.
 */
public class GameClient {

    private Client client;
    private LoggedInUser user;
    private LouieGame game;
    private Player player;


    public GameClient(LoggedInUser user, LouieGame game) throws IOException {


        this.client = new Client();
        this.user = user;
        this.game = game;
        client.start();
        Network.register(client);


        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {

                if (o instanceof HitResponse) {
                    HitResponse r = (HitResponse) o;
                    hitPlayer(r.getPlayer());
                                  }
                if (o instanceof StartGameResponse) {
                    StartGameResponse startGameResponse = (StartGameResponse) o;
                    startGame(startGameResponse);
                }

                if (o instanceof AddPlayerResponse) {
                    AddPlayerResponse addPlayerResponse = (AddPlayerResponse) o;
                    player = addPlayerResponse.getPlayer();
                    System.out.println(addPlayerResponse.getText());
                }

                if(o instanceof ServerUpdate){
                    ServerUpdate update = (ServerUpdate) o;
                    updateClient(update);
                }
            }

            @Override
            public void connected(Connection connection) {
                sendAddPlayerRequest(connection);

            }

            @Override
            public void disconnected(Connection connection) {

            }
        });

        client.connect(5000, Network.HOSTIP,Network.TCPPORT,Network.UDPPORT);


    }

    private void updateClient(ServerUpdate update) {
        GameScreen screen = (GameScreen) game.getScreen();
        screen.updateFromServer(update);

    }


    private void hitPlayer(Player responsePlayer) {
        if(player.getPlayerNumber() != responsePlayer.getPlayerNumber()){
            GameScreen screen = (GameScreen) game.getScreen();
            screen.hitPlayer(responsePlayer.getPlayerNumber());
       }
    }

    public void sendAddPlayerRequest(Connection connection) {
        AddPlayerRequest addPlayerRequest = new AddPlayerRequest();
        addPlayerRequest.setUserId(user.getId());
        connection.sendTCP(addPlayerRequest);
    }

    public void sendHitRequest() {
        HitRequest hitRequest = new HitRequest();
        hitRequest.setPlayer(player);
        client.sendTCP(hitRequest);
    }

    private void startGame(StartGameResponse startGameResponse) {
        ConfigGameScreen screen = (ConfigGameScreen) game.getScreen();
        screen.setStartGame(true, startGameResponse.getPlayers(),startGameResponse.getSettings(), player);

    }


    public Client getClient() {
        return client;
    }

    public Player getPlayer() {
        return player;
    }

    public void sendStartGameRequest(GameSettings settings){
        if(this.getClient().isConnected()){
            StartGameRequest startGameRequest = new StartGameRequest();
            startGameRequest.setGameSettings(settings);
            startGameRequest.setPlayer(this.getPlayer());

            this.getClient().sendTCP(startGameRequest);
        }
    }
}
