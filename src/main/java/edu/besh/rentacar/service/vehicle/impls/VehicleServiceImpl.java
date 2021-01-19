/*
 * VehicleServiceImpl
 *
 * Version 1
 *
 * Artem Beshevli
 */

package edu.besh.rentacar.service.vehicle.impls;

import edu.besh.rentacar.entity.Vehicle;
import edu.besh.rentacar.fakedb.FakeSet;
import edu.besh.rentacar.repository.VehicleRepository;
import edu.besh.rentacar.service.vehicle.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//  имплементация викл интерфейса
@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    VehicleRepository repository;  //  Инициализация бина, который отвечает за работу с базой Монго

    @Autowired              // инициализация бина, который записывает в базу хардкод данные
    FakeSet fakeSet;

    @PostConstruct  //  аннотация , которая заставляет сработать метод сразу после создания всех бинов
    void init(){
        repository.deleteAll();
        repository.saveAll(fakeSet.getVehicles());  // запись в базу хардкод данных
    }

    // реализация метода ГЕТ ОЛ из КРУДа
    @Override
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    // реализация метода РИД из Круда
    @Override
    public Vehicle get(int id) {
        return repository.findById(id).orElse(null);
    }

    // реализация метода КРИЕЙТ  из КРУДа
    @Override
    public Vehicle create(Vehicle vehicle) {

        // получене максимального ай-ди в базе

        // Трансформ список виклов в список ай-дишников
        List<Integer> listId = this.getAll().stream()
                .mapToInt(Vehicle::getId).boxed().collect(Collectors.toList());

        int[] arrayId = new int[listId.size()];

        for (int i = 0; i <listId.size() ; i++) {
            arrayId[i] = (int) listId.get(i);
        }

        //  сортировка списка ай-дишек по возрастанию методом бубла

        int[] listIdSorted = this.bubbleSort(arrayId);

        //  берем последний ай-ди  - он и будет максимальным
        int maxId = listIdSorted[listIdSorted.length-1];
        // записываем в базу MongoDb
        if (vehicle.getHourBack() == null) vehicle.setHourBack(0);
        vehicle.setId(repository.findAll().stream().mapToInt(item -> item.getId())
                .boxed().max(Integer::compareTo).orElse(1) + 1);
        return repository.save(vehicle);
    }

    // реализация метода АПДЕЙТ из КРУДа
    @Override
    public Vehicle edit(Vehicle vehicle) {
        if(vehicle.getHourBack() == null) vehicle.setHourBack(0);
        return repository.save(vehicle); }

    // реализация метода ДЕЛИТ из КРУДа
    @Override
    public void delete(int id) {repository.deleteById(id);}

    // сортировка по стиоимости аренды (по возрастанию)
    public List<Vehicle> sortByRentalFee(){
        return this.getAll().stream().sorted(Comparator.comparing(Vehicle::getRentalFee))
                .collect(Collectors.toList());
    }


    //  рекурсивный байнари поиск по стоимости аренды - самый быстрый поск по списку
    public double binarySearchByRentalFeeRecursively(
            List<Integer> sortedArrayList, int key, int low, int high) {
        int middle = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (key == sortedArrayList.get(middle)) {
            return middle;
        } else if (key < sortedArrayList.get(middle)) {
            return binarySearchByRentalFeeRecursively(
                    sortedArrayList, key, low, middle - 1);
        } else {
            return binarySearchByRentalFeeRecursively(
                    sortedArrayList, key, middle + 1, high);
        }
    }

    //   поиск по буквам , входящим в название бренда
    public List<Vehicle> search(String letters) { ;
        return this.getAll().stream()
                .filter(car-> car.getBrand().toLowerCase().contains(letters.toLowerCase()))
                .collect(Collectors.toList());
    }

     // сортировка методом бубла
    private int[] bubbleSort(int[] numbers)
    {
        int tempVar;
        for (int i = 0; i < numbers.length; i++)
        {
            for(int j = 0; j < numbers.length; j++)
            {
                if( j< numbers.length-1 && numbers[i] > numbers[j + 1])
                {
                    tempVar = numbers [j + 1];
                    numbers [j + 1]= numbers [i];
                    numbers [i] = tempVar;
                }
            }
        }
        return numbers;
    }


}
