public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.loadToysFromFile("toys.txt");

        Toy prizeToy = toyStore.getPrizeToy();
        if (prizeToy != null) {
            System.out.println("Выигрышная игрушка: " + prizeToy.getName());
            toyStore.writeToFile("prize_toys.txt", prizeToy);

            prizeToy.setQuantity(prizeToy.getQuantity() - 1);
            toyStore.saveToysToFile("toys.txt");
        } else {
            System.out.println("Все призовые игрушки закончились.");
        }
    }
}

