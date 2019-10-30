<template>
    <v-app-bar
            app
            fixed
    >
        <v-toolbar-title>Print Service</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-toolbar-items>
            <v-btn icon
                   @click="createPrintOrder()">
                <v-icon>mdi-plus</v-icon>
            </v-btn>
            <template v-if="$vuetify.breakpoint.smAndUp">
                <v-switch
                        class="mt-5"
                        v-model="$vuetify.theme.dark"
                        append-icon="mdi-weather-night"
                        prepend-icon="mdi-white-balance-sunny"
                />
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