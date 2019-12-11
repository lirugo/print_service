<template>
    <div>
        <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
                <form>
                    <v-card-title>
                        <v-avatar color="indigo" size="48">
                            <v-img :src="user.picture"/>
                        </v-avatar>
                        <span class="headline ml-3" v-text="user.name"/>
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
                                                    autocomplete="off"
                                                    v-model="date.year"
                                                    :items="[2018, 2019, 2020]"
                                                    label="Year"
                                    />
                                </v-col>
                                <v-col cols="12" sm="6" class="pt-0 pb-0">
                                    <v-autocomplete class="ma-0 pa-0"
                                                    autocomplete="off"
                                                    v-model="date.month"
                                                    :items="monthList"
                                                    item-text="value"
                                                    item-value="id"
                                                    label="Month"
                                    />
                                </v-col>
                                <v-col cols="12" class="pa-0" v-if="date.year != null && date.month != null">
                                    <v-list class="pa-0">
                                        <v-list-item-group color="primary">
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-alert-circle-outline</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Done orders - {{monthData.orders}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-format-page-break</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Printed pages - {{monthData.pages}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-book-open-page-variant</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Printed sheets - {{monthData.sheets}}</v-list-item-title>
                                                </v-list-item-content>
                                            </v-list-item>
                                            <v-list-item disabled>
                                                <v-list-item-icon><v-icon>mdi-currency-usd</v-icon></v-list-item-icon>
                                                <v-list-item-content>
                                                    <v-list-item-title class="white--text">Salary - {{monthData.salary}} UAH</v-list-item-title>
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
    import moment from 'moment'

    export default {
        props: ['dialog', 'user', 'userStatistic'],
        data: () => ({
            pricePerSheet: 0.5,
            orderCount: 0,
            orders: null,
            monthList: [
                { id: '01', value: 'January'},
                { id: '02', value: 'February'},
                { id: '03', value: 'March'},
                { id: '04', value: 'April'},
                { id: '05', value: 'May'},
                { id: '06', value: 'June'},
                { id: '07', value: 'July'},
                { id: '08', value: 'August'},
                { id: '09', value: 'September'},
                { id: '10', value: 'October'},
                { id: '11', value: 'November'},
                { id: '12', value: 'December'},
            ],
            date: {
                year: null,
                month: null,
            },
            monthData: null,
        }),
        watch: {
            date: {
                handler(newVal){
                    this.calcMonthData()
                },
                deep: true,
            },
            userStatistic: function (newVal) {
                this.orderCount = newVal.orderCount
                this.orders = newVal.orders
            }
        },
        computed: {
            totalOrders() {
                let orders = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        if(order.orderStatus === 'DONE')
                            orders++
                    })
                }

                return Math.ceil(orders).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
            totalPages() {
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        if(order.orderStatus === 'DONE')
                            pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }

                return Math.ceil(pages).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
            totalSheets() {
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        if(order.orderStatus === 'DONE')
                            pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }

                return Math.ceil(pages/2).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
            totalSalary(){
                let pages = 0
                if(this.orders) {
                    this.orders.forEach(order => {
                        if(order.orderStatus === 'DONE')
                            pages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                    })
                }
                let sheets = pages / 2;

                return Math.ceil(sheets * this.pricePerSheet).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
            },
        },
        methods: {
            calcMonthData() {
                let countPages = 0
                let countOrders = 0
                if(
                    this.date.year != null &&
                    this.date.month != null &&
                    this.orders
                ) {
                    let startDate = moment('01-' + this.date.month + '-' + this.date.year, 'DD-MM-YYYY').format('YYYY-MM-DD')
                    let endDate = moment('01-' + this.date.month + '-' + this.date.year, 'DD-MM-YYYY').add(1, 'month').format('YYYY-MM-DD')

                    this.orders.forEach(order => {
                        let orderManufactureDate = moment(order.manufactureDate, 'DD-MM-YYYY')
                        if(orderManufactureDate.isValid()){
                            if(
                                order.orderStatus === 'DONE' &&
                                orderManufactureDate.isBetween(startDate, endDate)
                            ){
                                countOrders++
                                countPages += order.pages * order.copies / (order.printType === 'ONE_SIDE' ? 1 : 2)
                            }
                        }
                    })

                    this.monthData = {
                        orders: countOrders,
                        pages: Math.ceil(countPages),
                        sheets: Math.ceil(countPages / 2),
                        salary: Math.ceil(countPages / 2 * this.pricePerSheet)
                    }
                }
            },
            close(){
                this.$emit('onCloseUserStatisticDialog')
            },
        }
    }
</script>