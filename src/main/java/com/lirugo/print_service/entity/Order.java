package com.lirugo.print_service.entity;

import com.lirugo.print_service.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="ordr")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "executor_id")
    @Nullable
    private User executor;

    private String name;
    @Column(name = "group_name")
    private String groupName;
    private String description;
    @Column(name = "file_name")
    private String fileName;
    @Column(columnDefinition = "integer default 0")
    private int copies;
    @Column(columnDefinition = "integer default 0")
    private int pages;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "repeat_message", columnDefinition = "varchar(2048) default ''")
    private String repeatMessage;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_priority")
    private OrderPriority orderPriority;

    @Enumerated(EnumType.STRING)
    @Column(name = "print_type")
    private PrintType printType;

    @Enumerated(EnumType.STRING)
    @Column(name = "paper_type")
    private PaperType paperType;

    @Enumerated(EnumType.STRING)
    @Column(name = "color_type")
    private ColorType colorType;

    @Column(name = "desired_manufacture_date")
    private LocalDateTime desiredManufactureDate;

    @Column(name = "manufacture_date")
    private LocalDateTime manufactureDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(
            String name, User author, User executor, String groupName, String description,
            String fileName, int copies, int pages, String orderStatus, String orderPriority,
            String printType, String paperType, String colorType, String desiredManufactureDate
    ) {
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.groupName = groupName;
        this.description = description;
        this.fileName = fileName;
        this.copies = copies;
        this.pages = pages;
        this.orderStatus = orderStatus != null ? OrderStatus.valueOf(orderStatus.toUpperCase()) : OrderStatus.IN_PROGRESS;
        this.orderPriority = orderPriority != null ? OrderPriority.valueOf(orderPriority.toUpperCase()) : OrderPriority.LOW;
        this.printType = printType != null ? PrintType.valueOf(printType.toUpperCase()) : PrintType.ONE_SIDE;
        this.paperType = paperType != null ? PaperType.valueOf(paperType.toUpperCase()) : PaperType.A4;
        this.colorType = colorType != null ? ColorType.valueOf(colorType.toUpperCase()) : ColorType.COLOR;
        this.desiredManufactureDate = LocalDateTime.parse(desiredManufactureDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
