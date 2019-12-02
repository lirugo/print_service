package com.lirugo.print_service.graph_ql.mutatuion;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.service.OrderService;
import com.lirugo.print_service.service.UserService;

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
        User author = null;
        User executor = null;
        if(executorId != null)
            executor = userService.getById(executorId);

        Order order = new Order(
                name, author, executor, groupName, description, fileName, copies,
                pages, orderStatus, orderPriority, printType, paperType, colorType,
                manufactureDate
        );

        orderService.save(order);

        return order;
    }

}
