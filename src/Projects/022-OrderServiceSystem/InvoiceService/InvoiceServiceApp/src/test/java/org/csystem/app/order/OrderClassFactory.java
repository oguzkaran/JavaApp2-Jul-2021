package org.csystem.app.order;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public final class OrderClassFactory{
    //...

    private static String readFileFromClasspathWithDelim(String fileNameWithExtension, char delim)
    {
        StringBuilder sb = new StringBuilder();

        //Java 7 try-with resources. Type must be support AutoCloseable interface
        try(BufferedReader br = Files.newBufferedReader(Paths.get(OrderClassFactory.class.getClassLoader().getResource(fileNameWithExtension).toURI()))){
            String line;
            while((line = br.readLine()) != null)
                sb.append(line).append(delim);
        }
        catch (Throwable ex){
            System.out.println("Exception occurred during file reading");
            ex.printStackTrace(); //printStackStrace() methodu async çalışır. Deployment a yansıtılmaması gerekir.
        }

        return sb.toString();
    }

    private static List<Order> getOrderListFromStringWithDelim(String ordersStr, char delim)
    {
        //null da bir adrestir fakat hiç bir nesneyi göstermez. Genelde memoryde 0. index i gösterir.
        List<Order> orderList = null; //Derleyici senin kodunu bilmek zorunda değildir.

        try {
            String regex = "[" + delim + "]";

            List<String> orderStrList = Arrays.stream(ordersStr.split(regex)).collect(Collectors.toList());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            orderList = orderStrList.stream()
                    .map(orderStr -> {
                        String[] orderElemStr = orderStr.split("[,]");

                        return Order.builder()
                                .setOrderId(Long.parseLong(orderElemStr[0]))
                                .setOrderDate(LocalDate.parse(orderElemStr[1].split("[ ]")[0], formatter))
                                .setClientId(Integer.parseInt(orderElemStr[2]))
                                .build();
                    })
                    .collect(Collectors.toList());

        }
        catch (Throwable ex){
            System.out.println("Exception occurred was received while parsing a read file");
            ex.printStackTrace(); //Aync çalışır. Yazılımcının yazdığı koda yöneliktir. Deployment a yansıtılmaz.
        }

        return orderList;
    }

    public OrderClassFactory() {}

    public static Order getRandomOrder() //Java nın direkt olarak desteklemediği sınıflar arası
    {
        return getRandomOrder(new Random());
    }

    public static Order getRandomOrder(Random random) //Java nın direkt olarak desteklemediği sınıflar arası
    {	//ilişkilerden "Association"
        List<Order> orderList = getAllOrders();

        return orderList.get(random.nextInt(orderList.size()));
    }

    //Core methodlar aşağıya yazılır.
    public static List<Order> getAllOrders()
    {
        String ordersStr = readFileFromClasspathWithDelim("orders.txt", '*');

        return getOrderListFromStringWithDelim(ordersStr, '*');
    }
}