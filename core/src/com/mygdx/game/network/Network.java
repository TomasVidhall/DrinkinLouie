package com.mygdx.game.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;
import com.mygdx.game.model.Player;
import com.mygdx.game.network.GameClient;
import com.mygdx.game.network.HitRequest;
import com.mygdx.game.network.HitResponse;

/**
 * Created by Administratör on 2014-09-19.
 */
public class Network {
    static public final int TCPPORT = 54555, UDPPORT = 54577;
    public static final String HOSTIP = "192.168.0.194";

    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(AddPlayerResponse.class);
        kryo.register(AddPlayerRequest.class);
        kryo.register(String.class);
        kryo.register(HitRequest.class);
        kryo.register(HitResponse.class);
        kryo.register(GameClient.class);
        kryo.register(Client.class);
        kryo.register(java.net.Inet4Address.class);
        kryo.register(StartGameRequest.class);
        kryo.register(StartGameResponse.class);
        kryo.register(Player.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(com.badlogic.gdx.math.Vector2.class);
        kryo.register(com.mygdx.game.GameSettings.class);
        kryo.register(com.badlogic.gdx.graphics.Color.class);
    }

}