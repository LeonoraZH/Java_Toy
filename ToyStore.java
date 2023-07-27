import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        this.toys = new ArrayList<>();
    }

    public void loadToysFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Проверяем, что строка содержит все нужные данные
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    double weight = Double.parseDouble(parts[3].trim());
                    toys.add(new Toy(id, name, quantity, weight));
                } else {
                    System.out.println("Некорректные данные в файле: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Некорректный формат числа в файле.");
        }
    }

    public void saveToysToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Toy toy : toys) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewToy(int id, String name, int quantity, double weight) {
        toys.add(new Toy(id, name, quantity, weight));
    }

    public void updateToyWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                return;
            }
        }
        System.out.println("Игрушка с указанным ID не найдена.");
    }

    public Toy getPrizeToy() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        Random random = new Random();
        double randomValue = random.nextDouble() * totalWeight;

        double currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomValue <= currentWeight && toy.getQuantity() > 0) {
                return toy;
            }
        }

        return null;
    }

    public void writeToFile(String filename, Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(toy.getId() + "," + toy.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
