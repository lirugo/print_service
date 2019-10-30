<template>
    <v-app-bar
            app
            fixed
    >
        <v-toolbar-title>Print Service</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-toolbar-items>
            <v-btn icon>
                <v-icon>mdi-plus</v-icon>
            </v-btn>
        </v-toolbar-items>

        <template v-if="$vuetify.breakpoint.smAndUp">
            <v-switch
                    class="mt-7"
                    v-model="$vuetify.theme.dark"
                    append-icon="mdi-weather-night"
                    prepend-icon="mdi-white-balance-sunny"
            />
        </template>
    </v-app-bar>
</template>