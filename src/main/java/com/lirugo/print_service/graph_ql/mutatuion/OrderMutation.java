package com.lirugo.print_service.graph_ql.mutatuion;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.enums.OrderStatus;
import com.lirugo.print_service.service.OrderService;
import com.lirugo.print_service.service.UserService;
import com.lirugo.print_service.service.auth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class OrderMutation implements GraphQLMutationResolver {

    private final OrderService orderService;
    private final UserService userService;

    public OrderMutation(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public Order storeOrder(
            String name, String executorId, String groupName, String description,
            String fileName, int copies, int pages, String orderStatus, String orderPriority,
            String printType, String paperType, String colorType, String manufactureDate
    ) {
        //Get users
        User author = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        User executor = null;

        Order order = new Order(
                name, author, executor, groupName, description, fileName, copies,
                pages, orderStatus, orderPriority, printType, paperType, colorType,
                manufactureDate
        );

        orderService.save(order);

        return order;
    }

    public Order updateStatusOrder(
          int id, String orderStatus, String repeatMessage
    ) {
        //Get auth users
        User executor = userService.getById(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());

        Order order = orderService.getById(id);
        order.setExecutor(executor);
        order.setRepeatMessage(repeatMessage);
        order.setOrderStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));

        orderService.save(order);

        return order;
    }

}
