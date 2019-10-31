<template>
    <v-row
            cols="12"
            justify="center">
        <v-col
                cols="12"
                sm="6"
                md="4">
            <v-card
                    class="mx-auto"
            >
                <v-toolbar>
                    <v-toolbar-title>Printing Worker</v-toolbar-title>
                </v-toolbar>
                <v-list>
                    <v-list-item
                            v-for="item in items"
                            :key="item.title"
                            @click="statistic()"
                    >
                        <v-list-item-icon>
                            <v-icon>mdi-google-spreadsheet</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title v-text="item.title"></v-list-item-title>
                        </v-list-item-content>

                        <v-list-item-avatar>
                            <v-img :src="item.avatar"></v-img>
                        </v-list-item-avatar>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-col>
        <v-col
                cols="12"
                sm="6"
                md="4">
            <v-card
                    class="mx-auto"
            >
                <v-toolbar>
                    <v-toolbar-title>Users</v-toolbar-title>
                </v-toolbar>
                <v-list>
                    <v-list-item
                            v-for="item in items"
                            :key="item.title"
                    >
                        <v-list-item-content>
                            <v-list-item-title v-text="item.title"></v-list-item-title>
                        </v-list-item-content>

                        <v-list-item-avatar>
                            <v-img :src="item.avatar"></v-img>
                        </v-list-item-avatar>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-col>

        <!--        Dialog-->
        <v-dialog v-model="dialog" persistent max-width="600">
            <v-card>
                <v-list-item class="pt-3">
                    <v-list-item-avatar color="grey"></v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title class="headline">John Doe</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>

                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" sm="6" class="pt-0 pb-0">
                                <v-autocomplete
                                        :items="[2018, 2019, 2020]"
                                        label="Year"
                                ></v-autocomplete>
                            </v-col>
                            <v-col cols="12" sm="6" class="pt-0 pb-0">
                                <v-autocomplete
                                        :items="['January', 'February', 'Soccer', 'Basketball', 'Hockey', 'Reading', 'Writing', 'Coding', 'Basejump']"
                                        label="Month"
                                ></v-autocomplete>
                            </v-col>
                            <v-col cols="12" class="pa-0">
                                <v-list class="pa-0">
                                    <v-list-item-group color="primary">
                                        <v-list-item>
                                            <v-list-item-icon><v-icon>mdi-currency-usd</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title>Salary - 100</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        <v-list-item>
                                            <v-list-item-icon><v-icon>mdi-format-page-break</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title>Printed pages - 1024</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                        <v-list-item>
                                            <v-list-item-icon><v-icon>mdi-book-open-page-variant</v-icon></v-list-item-icon>
                                            <v-list-item-content>
                                                <v-list-item-title>Printed sheets - 1024</v-list-item-title>
                                            </v-list-item-content>
                                        </v-list-item>
                                    </v-list-item-group>
                                </v-list>
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn text @click="dialog = false">CLOSE</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    export default {
        data () {
            return {
                dialog: false,
                items: [
                    { icon: true, title: 'Jason Oner', avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg' },
                    { title: 'Travis Howard', avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg' },
                    { title: 'Ali Connors', avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg' },
                    { title: 'Cindy Baker', avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg' },
                ],
            }
        },
        methods: {
            statistic(){
                this.dialog = true
            }
        }
    }
</script>