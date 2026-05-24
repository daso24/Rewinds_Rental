package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class UpstashConnection
{
    private static JedisPool pool;

    static
    {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties"))
        {
            prop.load(input);

            String host = prop.getProperty("upstash.host");
            int port = Integer.parseInt(prop.getProperty("upstash.port"));
            String password = prop.getProperty("upstash.password");

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(10);

            pool = new JedisPool(poolConfig, host, port, 2000, password, true);
            
            System.out.println("Conexión a Upstash configurada correctamente.");
        }
        catch (IOException | NumberFormatException e)
        {
            System.err.println("Error al cargar config.properties: " + e.getMessage());
        }
    }

    public static Jedis getResource()
    {
        return pool.getResource();
    }
}