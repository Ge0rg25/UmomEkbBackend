package ru.umom.umombackend.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.DishDto;
import ru.umom.umombackend.dto.OrderDto;
import ru.umom.umombackend.dto.UserDto;
import ru.umom.umombackend.errors.common.OrganizationNotExsitsError;
import ru.umom.umombackend.models.DishEntity;
import ru.umom.umombackend.models.OrderEntity;
import ru.umom.umombackend.models.UserEntity;
import ru.umom.umombackend.repositories.DishRepository;
import ru.umom.umombackend.repositories.OrderRepository;
import ru.umom.umombackend.repositories.OrganizationRepository;
import ru.umom.umombackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {

    OrderRepository orderRepository;
    UserRepository userRepository;
    DishRepository dishRepository;
    OrganizationRepository organizationRepository;

    public ResponseEntity<?> create(Jwt jwt, OrderDto.Request.Create dto) {
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder().id(jwt.getSubject()).name(jwt.getClaim("name")).build());

        List<DishEntity> dishes = dishRepository.findAllById(dto.dishesId());

        OrderEntity order = OrderEntity.builder().delivery(dto.delivery()).user(user).dishes(dishes).build();
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> complete(OrderDto.Request.Complete dto) {
        OrderEntity order = orderRepository.findById(dto.id()).orElseThrow();
        order.setCompleted(true);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAllByOrganization(OrderDto.Request.GetAllByOrganization dto) {
        List<OrderEntity> orders = organizationRepository.findById(dto.organizationId()).orElseThrow(OrganizationNotExsitsError::new).getOrders();
        List<OrderDto.Response.BaseResponse> response = new ArrayList<>();

        for (OrderEntity order : orders) {
            List<DishDto.Response.Dish> dishes = new ArrayList<>();
            for (DishEntity dish : order.getDishes()) {
                dishes.add(new DishDto.Response.Dish(dish.getId(), dish.getTitle(), dish.getDescription(), dish.getPrice(), dish.getCalories(), dish.getProteins(), dish.getFats(), dish.getCarbohydrates(), dish.getCategory().getId(), dish.getPhotoId()));
            }

            UserDto.Response.User user = new UserDto.Response.User(order.getUser().getId(), order.getUser().getName(), order.getUser().getFloor(), order.getUser().getWorkstation());
            response.add(new OrderDto.Response.BaseResponse(order.getId(), order.isDelivery(), dishes, user, order.getCreatedAt(), order.isCompleted()));
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAll(Jwt jwt) {
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder().id(jwt.getSubject()).name(jwt.getClaim("name")).orders(new ArrayList<>()).build());
        List<OrderEntity> orders = user.getOrders();
        UserDto.Response.User userDto = new UserDto.Response.User(user.getId(), user.getName(), user.getFloor(), user.getWorkstation());
        List<OrderDto.Response.BaseResponse> response = new ArrayList<>();
        for (OrderEntity order : orders) {
            List<DishDto.Response.Dish> dishes = new ArrayList<>();
            for (DishEntity dish : order.getDishes()) {
                dishes.add(new DishDto.Response.Dish(dish.getId(), dish.getTitle(), dish.getDescription(), dish.getPrice(), dish.getCalories(), dish.getProteins(), dish.getFats(), dish.getCarbohydrates(), dish.getCategory().getId(), dish.getPhotoId()));

            }
            response.add(new OrderDto.Response.BaseResponse(order.getId(), order.isDelivery(), dishes, userDto, order.getCreatedAt(), order.isCompleted()));
        }
        return ResponseEntity.ok(response);
    }


}
