<template>
    <div>
        <v-row>
            <v-col>
                <div class="text-center">
                    <v-pagination
                            v-model="page"
                            :length="12"
                    />
                </div>
            </v-col>
        </v-row>
        <v-row
                cols="12"
        >

            <v-col
                    v-for="order in orders"
                    :key="order.id"
                    sm="4"
                    md="3"
            >

                <v-card
                        outlined
                >
                    <span style="position: relative; float: right" class="text-right right ma-2"
                          v-text="'#' + order.id"/>
                    <v-list-item>
                        <v-list-item-avatar color="grey" size="50"/>
                        <v-list-item-content>
                            <v-list-item-title class="headline">John Doe</v-list-item-title>
                            <v-list-item-subtitle>Room 000</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-list-item three-line>
                        <v-list-item-content>
                            <div class="overline">{{order.createdAt}} - Date of request</div>
                            <div class="overline">{{order.manufactureDate}} - Date of manufacturing</div>
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
                        <v-btn  icon>
                            <v-icon>mdi-download</v-icon>
                        </v-btn>
                        <v-spacer/>
                        <v-chip color="green" outlined v-text="order.orderStatus"/>
                        <v-spacer/>
                        <v-chip outlined color="white" v-text="order.groupName"/>
                    </v-card-actions>
                </v-card>

            </v-col>
        </v-row>
    </div>
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