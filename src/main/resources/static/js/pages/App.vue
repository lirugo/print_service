<template>
    <v-app>
        <v-content>
            <v-toolbar>
                <v-toolbar-title>Print Service</v-toolbar-title>

                <v-spacer></v-spacer>

                <v-switch
                        class="mt-7"
                        v-model="$vuetify.theme.dark"
                        append-icon="mdi-weather-night"
                        prepend-icon="mdi-white-balance-sunny"
                />
            </v-toolbar>

            <v-container fluid
                         fill-height
            >
                <ListOfOrders/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import ListOfOrders from 'components/print/ListOfOrders.vue'
    export default {
        components: {
            ListOfOrders,
        }
    }
</script>