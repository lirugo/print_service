<template>
    <v-list>
        <v-list-item
                v-for="user in getUsers"
                :key="user.id"
                @click="onOpenUserStatisticDialog(user.id)"
                v-if="user.roles.includes('ADMIN')"
        >
            <v-list-item-icon>
                <v-icon>mdi-google-spreadsheet</v-icon>
            </v-list-item-icon>

            <user-statistic-dialog
                    :dialog="dialog"
                    :user="user"
                    :userStatistic="userStatistic"
                    @onCloseUserStatisticDialog="onCloseUserStatisticDialog()"
            />

            <v-list-item-content>
                <v-list-item-title v-text="user.name"/>
                <v-list-item-subtitle v-text="'Last visit ' + user.lastVisit"/>
            </v-list-item-content>

            <v-list-item-avatar>
                <v-img :src="user.picture"/>
            </v-list-item-avatar>
        </v-list-item>
    </v-list>
</template>

<script>
    import UserStatisticDialog from 'components/statistic/UserStatisticDialog.vue'
    import {mapGetters} from 'vuex'
    import axios from "axios";

    export default {
        components: {
            UserStatisticDialog,
        },
        data () {
            return {
                dialog: false,
                userStatistic: null,
            }
        },
        computed: {
            ...mapGetters(['getUsers']),
        },
        methods: {
            onOpenUserStatisticDialog(userId){
                this.dialog = true
                axios.post('/graphql', {
                    query: `
                    {
                        orders(filter: {executorId: ${userId} }) {
                             id, name, pages, copies, printType,
                             manufactureDate, orderStatus,
                        },
                        orderCount(filter: {executorId: ${userId}})
                    }
                  `,
                }).then(res => {
                    this.userStatistic = res.data.data
                })
            },
            onCloseUserStatisticDialog(){
                this.dialog = false
            },
        },
        created() {
        }
    }
</script>