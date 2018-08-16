package strategies;

import java.util.Comparator;
import java.util.PriorityQueue;

import automail.MailItem;
import automail.PriorityMailItem;
import automail.Robot;
import automail.StorageTube;
import exceptions.TubeFullException;

public class MyMailPool implements IMailPool {
    private static final int MAX_WEIGHT = 2000;
    private static final int NUMBER_OF_ROBOTS = 3;
    private Robot[] robotArmy;
    
    private PriorityQueue<MailItem> heavyItems;
    private PriorityQueue<MailItem> lightItems;
    private PriorityQueue<PriorityMailItem> priorityItems;
    
    /**
     * Create empty priority queues for each type of mail item and an array
     * for the robots
     */
    public MyMailPool() {
        heavyItems = new PriorityQueue<>(new Comparator<MailItem>() {
            @Override
            public int compare(MailItem mail1, MailItem mail2) {
                return mail1.getDestFloor() - mail2.getDestFloor();
            }
        });
        lightItems = new PriorityQueue<>(heavyItems);
        
        priorityItems = new PriorityQueue<>(new Comparator<PriorityMailItem>() {
            @Override
            public int compare(PriorityMailItem mail1, PriorityMailItem mail2) {
                return mail2.getPriorityLevel() - mail1.getPriorityLevel();
            }
        });
        robotArmy = new Robot[NUMBER_OF_ROBOTS];
    }

    @Override
    /**
     * The mail item is processed and sorted into one of three piles depending
     * on the weight and priority of the mail item
     */
    public void addToPool(MailItem mailItem) {
        
        // Check whether mail item has any priority on it
        if (mailItem instanceof PriorityMailItem) {
            priorityItems.add((PriorityMailItem) mailItem);
        }
        
        // Sort between heavy and light items
        else if (mailItem.getWeight() <= MAX_WEIGHT) {
            lightItems.add(mailItem);
        } else {
            heavyItems.add(mailItem);
        }
    }

    @Override
    /**
     * Invoked once per frame reference of time - checks for mail items and
     * sends robots to deliver the items
     */
    public void step() {
        
        // Check if each robot is available to deliver some mail items
        for (int i=0; i<robotArmy.length; i++) {
            if (robotArmy[i] != null) {
                fillStorage(robotArmy[i]);
            }
        }
    }

    @Override
    public void registerWaiting(Robot robot) {
        for (int i=0; i<robotArmy.length; i++) {
            if (robotArmy[i] == null) {
                robotArmy[i] = robot;
                break;
            }
        }
    }

    @Override
    public void deregisterWaiting(Robot robot) {
        for (int i=0; i<robotArmy.length; i++) {
            if (robotArmy[i] == robot) {
                robotArmy[i] = null;
                break;
            }
        }
    }
    
    /**
     * Fill the robot's storage tube with as much mail items as possible,
     * prioritising priority mail items and mail items closer to the floor
     * @param currentRobot
     */
    public void fillStorage(Robot currentRobot) {
        StorageTube tube = currentRobot.getTube();
        
        // Determine the mail items that the robot can carry and continue
        // loading the tube until full or no more mail items to deliver
        if (currentRobot.isStrong()) {
            addToTube(tube, priorityItems, "strong");
            addToTube(tube, heavyItems);
            addToTube(tube, lightItems);
        } else {
            addToTube(tube, priorityItems, "weak");
            addToTube(tube, lightItems);
        }
        currentRobot.dispatch();
    }
    
    /**
     * Add mail items from storage into a tube while there are mail items
     * and the tube isn't full
     * @param tube
     * @param storage
     */
    public void addToTube(StorageTube tube, PriorityQueue<MailItem> storage) {
        while (!tube.isFull() && storage.size() > 0) {
            try {
                tube.addItem(storage.poll());
            } catch (TubeFullException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Add priority mail items into the robot's tube until full or until there
     * are no more mail items in storage
     * @param tube
     * @param storage
     * @param robotType
     */
    public void addToTube(StorageTube tube, PriorityQueue<PriorityMailItem> storage, String robotType) {
        switch (robotType) {
            // Strong robot - a.k.a a robot that can carry any weight of mail item
            case "strong":
                while (!tube.isFull() && storage.size() > 0) {
                    try {
                        tube.addItem(storage.poll());
                    } catch (TubeFullException e) {
                        e.printStackTrace();
                    }
                }
                break;
            // Weak robot - the robot that cannot carry more than 2000g
            case "weak":
                while (!tube.isFull() && storage.size() > 0 && storage.peek().getWeight() <= MAX_WEIGHT) {
                    try {
                        tube.addItem(priorityItems.poll());
                    } catch (TubeFullException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
