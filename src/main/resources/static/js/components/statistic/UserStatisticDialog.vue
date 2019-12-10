<template>
    <div>
        <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
                <form>
                    <v-card-title>
                        <v-spacer/>
                        <span class="headline" v-text="user.name"/>
                        <v-spacer/>
                        <v-btn outlined small fab
                               @click="close()">
                            <v-icon dark small>mdi-close</v-icon>
                        </v-btn>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col>
                                    <p class="font-weight-regular grey--text ma-0">Total information</p>
                                    <v-list-item-group color="primary">
                                        <v-list-item disabled>
                                            <v-list-item-icon><v-icon>mdi-alert-circle-outline</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title class="white--text">Done orders - {{totalOrders}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        <v-list-item disabled>
                                            <v-list-item-icon><v-icon>mdi-format-page-break</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title class="white--text">Printed pages - {{totalPages}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        <v-list-item disabled>
                                            <v-list-item-icon><v-icon>mdi-book-open-page-variant</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title class="white--text">Printed sheets - {{totalSheets}}</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        <v-list-item disabled>
                                            <v-list-item-icon><v-icon>mdi-currency-usd</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title class="white--text">Total Salary - {{totalSalary}} UAH</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-list-item-group>
                                </v-col>
                            </v-row>

                            <v-row>
                                <v-col cols="12" sm="12" class="ma-0">
                                    <p class="font-weight-regular grey--text ma-0">Month information</p>
                                </v-col>
                                <v-col cols="12" sm="6" class="pt-0 pb-0">
                                    <v-autocomplete class="ma-0 pa-0"
                                                    :items="[2018, 2019, 2020]"
                                                    label="Year"
                                    />
                                </v-col>
                                <v-col cols="12" sm="6" class="pt-0 pb-0">
                                    <v-autocomplete class="ma-0 pa-0"
                                                    :items="['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']"
                                                    label="Month"
                                    />
                                </v-col>
                                <v-col cols="12" class="pa-0">
                                    <v-list class="pa-0">
                                        <v-list-item-group color="primary">
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-alert-circle-outline</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Done orders - 218</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-format-page-break</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Printed pages - 1024</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-book-open-page-variant</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Printed sheets - 1024</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-currency-usd</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Salary - 100</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                        </v-list-item-group>
                                    </v-list>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                </form>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>

    export default {
        props: ['dialog', 'user', 'userStatistic'],
        data: () => ({
            pricePerSheet: 0.4,
            totalOrders: 0,
            orders: null,
        }),
        watch: {
            userStatistic: function (newVal) {
                this.totalOrders = newVal.orderCount
                this.orders = newVal.orders
            }
        },
        computed: {
            totalPages() {
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }

                return Math.ceil(pages).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
            totalSheets() {
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }

                return Math.ceil(pages/2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
            totalSalary(){
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }
                let sheets = pages / 2;

                return Math.ceil(sheets * this.pricePerSheet).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
        },
        methods: {
            getUserStatistic(){
            },
            close(){
                this.$emit('onCloseUserStatisticDialog')
            },
        }
    }
</script>