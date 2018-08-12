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
	private static final int MAX_TAKE = 4;
	private Robot[] robotArmy;  // There's only three of these.
	private int numberOfRobots = 3;
	
	private PriorityQueue<MailItem> heavyItems;
	private PriorityQueue<MailItem> lightItems;
	private PriorityQueue<PriorityMailItem> priorityItems;
	private PriorityMailItem currentPriority;
	
	public MyMailPool() {
		
		heavyItems = new PriorityQueue<>(new Comparator<MailItem>() {
			@Override
			public int compare(MailItem mail1, MailItem mail2) {
				return mail1.getDestFloor() - mail2.getDestFloor();
			}
		});
		lightItems = new PriorityQueue<>(new Comparator<MailItem>() {
			@Override
			public int compare(MailItem mail1, MailItem mail2) {
				return mail1.getDestFloor() - mail2.getDestFloor();
			}
		});
		priorityItems = new PriorityQueue<>(new Comparator<PriorityMailItem>() {
			@Override
			public int compare(PriorityMailItem mail1, PriorityMailItem mail2) {
				return mail2.getPriorityLevel() - mail1.getPriorityLevel();
			}
		});
		robotArmy = new Robot[numberOfRobots];
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
		
		// Check if there are any priority mail items and send any robots
		// available to deliver the mail items - Assumes each priority mail is
		// processed once at a time
		if (getPriorityItemSize() > 0) {
			currentPriority = priorityItems.poll();
			int robotNumber = chooseAppropriateRobot(currentPriority);
			
			// Give robot the mail item
			try {
				giveRobotMail(currentPriority, robotNumber);
			} catch (TubeFullException e) {
				e.printStackTrace();
			}
			
			// Check if there are any mail items conveniently close to the
			// floor of the priority mail item
			if (robotArmy[robotNumber].isStrong()) {
				collectOtherMail(currentPriority.getDestFloor(), robotNumber, heavyItems);
			}
			collectOtherMail(currentPriority.getDestFloor(), robotNumber, lightItems);
		}
		
	}

	@Override
	public void registerWaiting(Robot robot) {
		for (Robot currentRobot : robotArmy) {
			if (currentRobot == null) {
				currentRobot = robot;
			}
		}
	}

	@Override
	public void deregisterWaiting(Robot robot) {
		for (Robot currentRobot : robotArmy) {
			if (currentRobot.equals(robot)) {
				currentRobot = null;
			}
		}
	}
	
	public int getPriorityItemSize() {
		return new Integer(priorityItems.size());
	}
	
	/**
	 * Chooses the robot that will be able to deliver the mail item and returns
	 * the index of that robot
	 * @param mailitem
	 * @return
	 */
	public int chooseAppropriateRobot(MailItem mailItem) {
		if (mailItem.getWeight() <= MAX_WEIGHT) {
			// Weight isn't too heavy, any robot will do
			for (int i=0; i<robotArmy.length; i++) {
				if (robotArmy[i] != null) {
					return i;
				}
			}
		} else {
			for (int i=0; i<robotArmy.length; i++) {
				if (robotArmy[i] != null && robotArmy[i].isStrong()) {
					return i;
				}
			}
		}
		// No robots available for the task
		return -1;
	}
	
	/**
	 * Fill up the storage tube of the robot with some mail
	 * @param mail
	 * @param robotNumber
	 * @throws TubeFullException 
	 */
	public void giveRobotMail(MailItem mail, int robotNumber) throws TubeFullException {
		StorageTube tube = robotArmy[robotNumber].getTube();
		if (!tube.isFull()) {
			tube.addItem(mail);
		}
	}
	
	public void collectOtherMail(int floorNumber, int robotNumber, PriorityQueue<MailItem> items) {
		
	}

}
