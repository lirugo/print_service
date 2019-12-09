<template>
    <v-container>
        <v-row cols="12"
               align="start"
        >
            <v-col sm="12">
                <div class="text-center">
                    <v-pagination
                            v-model="page"
                            :length="12"
                    />
                </div>
            </v-col>

            <v-col
                    v-for="order in orders"
                    :key="order.id"
                    xs="12"
                    sm="6"
                    md="6"
                    lg="3"
            >
                <v-card
                        outlined
                        dense
                        elevation="2"
                >
                    <v-system-bar color="orange darken-2" lights-out v-if="order.orderPriority == 'HIGH'">High priority</v-system-bar>
                    <v-system-bar color="grey darken-2"lights-out v-else-if="order.orderPriority == 'MEDIUM'">Medium priority</v-system-bar>
                    <v-system-bar color="grey darken-3" lights-out v-else-if="order.orderPriority == 'LOW'">Low priority</v-system-bar>

                    <span style="position: relative; float: right" class="text-right right ma-2"
                          v-text="'#' + order.id"/>
                    <v-list-item>
                        <v-list-item-avatar color="grey" size="50">
                            <v-img :src="order.author.picture"/>
                        </v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline" v-text="order.author.name"/>
                            <v-list-item-subtitle>{{order.author.email}} - room {{order.author.room}}</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                    <v-divider></v-divider>

                    <v-list-item three-line>
                        <v-list-item-content>
                            <div class="overline">{{order.createdAt}} - Date of request</div>
                            <div class="overline">{{order.manufactureDate}} - Desired date of manufacturing</div>
                            <v-list-item-title class="headline mb-1" v-text="order.name"/>
                            <v-list-item-subtitle v-text="order.description"/>
                            <div class="overline mt-2">Copies - {{order.copies}}</div>
                            <div class="overline">Pages - {{order.pages}}</div>
                            <div class="overline">Type of paper - {{order.paperType}}</div>
                            <div class="overline">Type of print - {{order.printType}}</div>
                            <div class="overline">Type of color - {{order.colorType}}</div>
                        </v-list-item-content>
                    </v-list-item>

                    <v-card-actions>
                            <v-btn :href="'/static/order/' + order.fileName" icon>
                                <v-icon>mdi-download</v-icon>
                            </v-btn>
                        <v-spacer/>
                        <v-chip color="green" v-if="order.orderStatus == 'DONE'" outlined v-text="order.orderStatus"/>
                        <v-chip color="orange" v-else-if="order.orderStatus == 'IN_PROGRESS'" outlined v-text="order.orderStatus"/>
                        <v-chip color="red" v-else outlined v-text="order.orderStatus"/>
                        <v-spacer/>
                        <v-chip outlined color="white" v-text="order.groupName"/>
                    </v-card-actions>

                    <div v-if="order.executor">
                        <v-divider></v-divider>
                        <v-list-item>
                            <v-list-item-avatar color="grey" size="50">
                                <v-img :src="order.executor.picture"/>
                            </v-list-item-avatar>
                            <v-list-item-content>
                                <v-list-item-title class="headline" v-text="order.executor.name"/>
                                <v-list-item-subtitle>{{order.executor.email}} - room {{order.executor.room}}</v-list-item-subtitle>
                                <div class="overline">{{order.updatedAt}} - Date of executing</div>
                            </v-list-item-content>
                        </v-list-item>
                    </div>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        created() {
            this.fetchOrdersAction()
        },
        computed: {
            ...mapState(['orders'])
        },
        methods: {
            ...mapActions(['fetchOrdersAction']),
        },
        data: () => ({
            page:1
        })
    }
</script>