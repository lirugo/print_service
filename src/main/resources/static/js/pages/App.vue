<template>
    <v-app>

        <toolbar @createPrintOrder="onCreatePrintOrder()"/>
        <v-content>
            <v-container fluid
                         fill-height
            >
                <router-view></router-view>
            </v-container>
        </v-content>

        <!-- CreatePrintOrder Dialog -->
        <create-order-dialog
                :createPrintOrderDialog="openCreatePrintOrderDialog"
                @closePrintOrderDialog="onClosePrintOrderDialog()"
        />

    </v-app>
</template>

<script>
    import Toolbar from 'components/Toolbar.vue'
    import ListOfOrders from 'components/print/ListOfOrders.vue'
    import CreateOrderDialog from 'components/print/CreateOrderDialog.vue'

    export default {
        components: {
            Toolbar,
            ListOfOrders,
            CreateOrderDialog,
        },
        data: () => ({
            openCreatePrintOrderDialog: false,
        }),
        methods: {
            onCreatePrintOrder(){
                this.openCreatePrintOrderDialog = true
            },
            onClosePrintOrderDialog(){
                this.openCreatePrintOrderDialog = false
            },
        }
    }
</script>