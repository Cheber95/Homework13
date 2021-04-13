package ru.geekbraines;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable{
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    private static CyclicBarrier cbStart;
    private static CountDownLatch cdFinish;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public static int getCarsCount() {
        return CARS_COUNT;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }



    public static void getStart() {
        cbStart = new CyclicBarrier(CARS_COUNT);
    }
    public static void getFinish() {
        cdFinish = new CountDownLatch(CARS_COUNT);
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");

            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cbStart.await();

            //System.out.println(System.currentTimeMillis());
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            cbFinish.
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(Thread.currentThread().getName());

    }
    }


