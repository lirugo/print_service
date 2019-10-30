<template>
    <v-app>

        <toolbar/>
        <v-content>
            <v-container fluid
                         fill-height
            >
                <ListOfOrders/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import Toolbar from 'components/Toolbar.vue'
    import ListOfOrders from 'components/print/ListOfOrders.vue'
    export default {
        components: {
            Toolbar,
            ListOfOrders,
        }
    }
</script>