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
	private Robot[] robotArmy = new Robot[3];
	
	private PriorityQueue<MailItem> heavyItems;
	private PriorityQueue<MailItem> lightItems;
	private PriorityQueue<PriorityMailItem> priorityItems;
	
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
	 * dispatches robots to deliver the items
	 */
	public void step() {
		
		// Check if each robot is able to deliver some mail items
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
	
	public int getPriorityItemSize() {
		return new Integer(priorityItems.size());
	}
	
	public int getLightItemSize() {
		return new Integer(lightItems.size());
	}
	
	public int getHeavyItemSize() {
		return new Integer(heavyItems.size());
	}
	public void fillStorage(Robot currentRobot) {
		StorageTube tube = currentRobot.getTube();
		if (currentRobot.isStrong() == true) {
			while (!tube.isFull() && getPriorityItemSize() > 0) {
				try {
					tube.addItem(priorityItems.poll());
				} catch (TubeFullException e) {
					e.printStackTrace();
				}
			}
			while (!tube.isFull() && getHeavyItemSize() > 0) {
				try {
					tube.addItem(heavyItems.poll());
				} catch (TubeFullException e) {
					e.printStackTrace();
				}
			}
			while (!tube.isFull() && getLightItemSize() > 0) {
				try {
					tube.addItem(lightItems.poll());
				} catch (TubeFullException e) {
					e.printStackTrace();
				}
			}
			currentRobot.dispatch();
		}
		else {
			while (!tube.isFull() && getPriorityItemSize() > 0 && priorityItems.peek().getWeight() <= MAX_WEIGHT) {
				try {
					tube.addItem(priorityItems.poll());
				} catch (TubeFullException e) {
					e.printStackTrace();
				}
			}
			while (!tube.isFull() && getLightItemSize() > 0) {
				try {
					tube.addItem(lightItems.poll());
				} catch (TubeFullException e) {
					e.printStackTrace();
				}
			}
			currentRobot.dispatch();
		}
	}
}
