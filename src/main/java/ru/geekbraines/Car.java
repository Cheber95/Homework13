package ru.geekbraines;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable{
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    private static CountDownLatch cdStart;
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



    public static void start() {
        cdStart = new CountDownLatch(CARS_COUNT);
    }
    public static CountDownLatch getStart() {return cdStart; }
    public static void finish() { cdFinish = new CountDownLatch(CARS_COUNT); }
    public static CountDownLatch getFinish() {return cdFinish; }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");

            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdStart.countDown();
            cdStart.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            cdFinish.countDown();
            if (cdFinish.getCount() == CARS_COUNT - 1) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + this.name + " победитель!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(Thread.currentThread().getName());

    }
    }


