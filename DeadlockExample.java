public class DeadlockExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    private static final Object lock3 = new Object();

    static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread 1: Acquired lock 1");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("Thread 1: Acquired lock 2");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock3) {
                        System.out.println("Thread 1: Acquired lock 3");
                    }
                }
            }
        }
    }

    static class Thread2 extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("Thread 2: Acquired lock 2");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock3) {
                    System.out.println("Thread 2: Acquired lock 3");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock1) {
                        System.out.println("Thread 2: Acquired lock 1");
                    }
                }
            }
        }
    }

    static class Thread3 extends Thread {
        public void run() {
            synchronized (lock3) {
                System.out.println("Thread 3: Acquired lock 3");

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("Thread 3: Acquired lock 1");

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lock2) {
                        System.out.println("Thread 3: Acquired lock 2");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        Thread t3 = new Thread3();

        t1.start();
        t2.start();
        t3.start();
    }
}
