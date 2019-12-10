<template>
    <v-container fluid>
        <v-row v-if="orders.length < 1"
               cols="12"
               align="start"
        >
            <v-col sm="12">
                No any order yet...
            </v-col>
        </v-row>
        <v-row v-else
               cols="12"
               align="start"
        >
            <v-col sm="12">
                <div class="text-center">
                    <v-row justify="center">
                        <div>
                            <v-select
                                    v-model="paginator.limit"
                                    class="mt-1 pt-0 ml-2"
                                    style="max-width: 80px;"
                                    :items="paginator.pages"
                                    label="On page"
                            />
                        </div>
                        <div>
                            <v-pagination
                                    v-model="paginator.currentPage"
                                    :length="length"
                                    :total-visible="paginator.totalVisible"
                            />
                        </div>
                        <div class="mt-1">
                            <v-chip
                                    class="ma-2"
                                    small
                            >
                                Total {{ orderCount }} orders
                            </v-chip>
                        </div>
                    </v-row>
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
                <v-alert
                        v-if="order.orderStatus === 'REPEAT'"
                        dense
                        outlined
                        color="red"
                        type="error"
                >
                    Order will not be completed. Create new one.
                </v-alert>

                <v-hover v-slot:default="{ hover }">
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

                        <v-expand-transition>
                            <div
                                    v-if="hover && order.orderStatus === 'REPEAT' && order.repeatMessage"
                                    class="d-flex transition-fast-in-fast-out v-card--reveal pa-2 white--text"
                                    style="
                                        border-width: 2px 0 2px 0;
                                        border-style: solid;
                                        border-color: #F44336;
                                        height: 100%;"
                            >
                                {{order.repeatMessage}}
                            </div>
                        </v-expand-transition>

                        <v-list-item three-line>
                            <v-list-item-content>
                                <div class="overline">{{order.createdAt}} - Date of request</div>
                                <div class="overline">{{order.manufactureDate}} - Desired date of manufacturing</div>
                                <v-list-item-title class="headline mb-1" v-text="order.name"/>
                                <v-list-item-subtitle v-text="order.description != 'null' ? order.description : 'Dont have description'"/>
                                <div class="overline mt-2">Copies - {{order.copies}}</div>
                                <div class="overline">Pages - {{order.pages}}</div>
                                <div class="overline">Type of paper - {{order.paperType}}</div>
                                <div class="overline">Type of print - {{order.printType}}</div>
                                <div class="overline">Type of color - {{order.colorType}}</div>
                            </v-list-item-content>
                        </v-list-item>

                        <v-card-actions>
                            <v-btn :href="'/static/order/' + order.fileName" target="_blank" icon>
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
                        <div v-else-if="user.roles.includes('ADMIN')">
                            <v-divider></v-divider>
                            <change-order-state-dialog :order="order" />
                        </div>
                    </v-card>
                </v-hover>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import ChangeOrderStateDialog from 'components/print/ChangeOrderStateDialog.vue'

    export default {
        components: {
            ChangeOrderStateDialog,
        },
        mounted() {
            this.fetchOrdersAction(this.paginator)
        },
        computed: {
            ...mapState(['orders', 'orderCount', 'user'])
        },
        methods: {
            ...mapActions(['fetchOrdersAction']),
            updatePaginator(){
                this.paginator.offset = this.paginator.limit * this.paginator.currentPage - this.paginator.limit
                this.length = Math.ceil(this.orderCount / this.paginator.limit)

                this.fetchOrdersAction(this.paginator)
            }
        },
        watch: {
            paginator: {
                handler(newPaginator) {
                    this.updatePaginator()
                },
                deep: true
            },
            length(){
                this.paginator.currentPage = 1
            },
            orderCount(){
                this.updatePaginator()
            },
        },
        data: () => ({
            length: 12,
            paginator: {
                limit: 10,
                offset: 0,
                currentPage: 1,
                totalVisible: 12,
                pages: [10, 20, 50, 100, 200, 500],
            }
        }),
    }
</script>