<template>
    <v-container
            fill-height
    >
        <v-row
                cols="12"
                justify="center"
        >
            <v-col
                    cols="12"
                    sm="6"
                    md="4">

                <v-skeleton-loader
                        :loading="loadingUsers"
                        transition="fade-transition"
                        height="94"
                        type="list-item-avatar-three-line"
                >
                    <v-card
                            class="mx-auto"
                    >

                        <v-toolbar>
                            <v-toolbar-title>Printing Workers</v-toolbar-title>
                        </v-toolbar>
                        <v-list>
                            <v-list-item
                                    v-for="user in getUsers"
                                    :key="user.id"
                                    @click="statistic()"
                                    v-if="user.roles.includes('ADMIN')"
                            >
                                <v-list-item-icon>
                                    <v-icon>mdi-google-spreadsheet</v-icon>
                                </v-list-item-icon>

                                <v-list-item-content>
                                    <v-list-item-title v-text="user.name"></v-list-item-title>
                                    <v-list-item-subtitle v-text="'Last visit ' + user.lastVisit"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-avatar>
                                    <v-img :src="user.picture"></v-img>
                                </v-list-item-avatar>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-skeleton-loader>
            </v-col>
            <v-col
                    cols="12"
                    sm="6"
                    md="4">
                <v-skeleton-loader
                        :loading="loadingUsers"
                        transition="fade-transition"
                        height="94"
                        type="list-item-avatar-three-line"
                >
                    <v-card
                            class="mx-auto"
                    >
                        <v-toolbar>
                            <v-toolbar-title>Users</v-toolbar-title>
                        </v-toolbar>
                        <v-list>
                            <v-list-item
                                    v-for="user in getUsers"
                                    :key="user.id"
                                    v-if="user.roles.includes('USER')"
                            >
                                <v-list-item-content>
                                    <v-list-item-title v-text="user.name"></v-list-item-title>
                                    <v-list-item-subtitle v-text="'Last visit ' + user.lastVisit"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-avatar>
                                    <v-img :src="user.picture"></v-img>
                                </v-list-item-avatar>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-skeleton-loader>
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
    </v-container>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'

    export default {
        data () {
            return {
                dialog: false,
                loadingUsers: true,
            }
        },
        computed: {
            ...mapGetters(['getUsers']),
        },
        watch: {
            getUsers: function () {
                this.loadingUsers = false
            }
        },
        methods: {
            ...mapActions(['fetchUsersAction']),
            statistic(){
                this.dialog = true
            }
        },
        created() {
            this.fetchUsersAction()
        }
    }
</script>