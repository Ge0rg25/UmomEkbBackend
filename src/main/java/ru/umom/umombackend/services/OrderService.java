package ru.umom.umombackend.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import ru.umom.umombackend.dto.OrderDto;
import ru.umom.umombackend.dto.UserDto;
import ru.umom.umombackend.errors.common.OrganizationNotExsitsError;
import ru.umom.umombackend.models.DishEntity;
import ru.umom.umombackend.models.OrderEntity;
import ru.umom.umombackend.models.OrganizationEntity;
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

    public ResponseEntity<?> create(Jwt jwt, OrderDto.Request.Create dto){
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder()
                .id(jwt.getSubject())
                .name(jwt.getClaim("name"))
                .build());

        List<DishEntity> dishes = dishRepository.findAllById(dto.dishesId());

        OrderEntity order = OrderEntity.builder()
                .delivery(dto.delivery())
                .user(user)
                .dishes(dishes)
                .build();
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(OrderDto.Request.Delete dto){
        OrderEntity order = orderRepository.findById(dto.id()).orElseThrow();
        orderRepository.delete(order);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAllByOrganization(OrderDto.Request.GetAllByOrganization dto){
        Set<OrderEntity> orders = organizationRepository.findById(dto.organizationId()).orElseThrow(OrganizationNotExsitsError::new).getOrders();
        List<OrderDto.Response.AdminOrders> response = new ArrayList<>();

        for(OrderEntity order: orders){
            List<String> dishesId = new ArrayList<>();
            for(DishEntity dish: order.getDishes()){
                dishesId.add(dish.getId());
            }

            UserDto.Response.User user = new UserDto.Response.User(order.getUser().getId(), order.getUser().getName(), order.getUser().getFloor(), order.getUser().getWorkstation());
            response.add(new OrderDto.Response.AdminOrders(order.getId(), user, dishesId, order.isDelivery()));
        }

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getAll(Jwt jwt){
        UserEntity user = userRepository.findById(jwt.getSubject()).orElse(UserEntity.builder()
                .id(jwt.getSubject())
                .name(jwt.getClaim("name"))
                .build());
        Set<OrderEntity> orders = user.getOrders();
        List<OrderDto.Response.UserOrders> response = new ArrayList<>();
        for(OrderEntity order: orders){
            List<String> dishesId = new ArrayList<>();
            for(DishEntity dish: order.getDishes()){
                dishesId.add(dish.getId());
            }
            response.add(new OrderDto.Response.UserOrders(order.getId(), order.isDelivery(), dishesId));
        }
        return ResponseEntity.ok(response);
    }


}
