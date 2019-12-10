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
                        <user-statistic-list />
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
        </v-row>
    </v-container>
</template>

<script>
    import UserStatisticList from 'components/statistic/UserStatisticList.vue'
    import {mapActions, mapGetters} from 'vuex'

    export default {
        components: {
            UserStatisticList,
        },
        data () {
            return {
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
        },
        created() {
            this.fetchUsersAction()
        }
    }
</script>