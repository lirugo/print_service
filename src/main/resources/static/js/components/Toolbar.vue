<template>
    <v-app-bar
            app
            fixed
    >
        <template v-if="$vuetify.breakpoint.smAndUp">
            <v-switch
                    class="mt-7 mr-5"
                    v-model="$vuetify.theme.dark"
                    append-icon="mdi-weather-night"
                    prepend-icon="mdi-white-balance-sunny"
            />
        </template>

        <template v-if="$vuetify.breakpoint.smAndUp">
            <v-toolbar-title>Print Service</v-toolbar-title>
        </template>

        <v-spacer></v-spacer>

        <v-toolbar-items>
            <v-btn icon to="/statistic/users">
                <v-icon>mdi-currency-usd</v-icon>
            </v-btn>
            <v-btn icon to="/orders">
                <v-icon>mdi-clipboard-list-outline</v-icon>
            </v-btn>
            <v-btn icon
                   class="mr-2"
                   @click="createPrintOrder()">
                <v-icon>mdi-plus</v-icon>
            </v-btn>

            <template v-if="$vuetify.breakpoint.smAndUp">
                <v-list-item-title>John Doe</v-list-item-title>
                <v-list-item-avatar color="grey" class="mr-2"/>
            </template>

            <v-btn icon>
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-toolbar-items>
    </v-app-bar>
</template>

<script>
    export default {
        methods: {
            createPrintOrder(){
                this.$emit('createPrintOrder')
            }
        }
    }
</script>