package com.lirugo.print_service.entity;

import com.lirugo.print_service.enums.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
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

    @Column(name = "manufacture_date")
    private LocalDateTime manufactureDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
