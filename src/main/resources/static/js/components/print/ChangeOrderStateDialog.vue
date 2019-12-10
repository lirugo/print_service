<template>
    <div>
        <v-btn
                @click="dialog = !dialog"
                tile light block
        >
            Change Order State
        </v-btn>

        <v-dialog v-model="dialog" persistent max-width="400px">
            <v-card>
                <form>
                    <v-system-bar lights-out>#{{order.id}} {{order.name}} {{order.groupName}}</v-system-bar>
                    <v-card-title>
                        <v-btn outlined small fab
                               color="green"
                               @click="create()">
                            <v-icon dark small>mdi-content-save</v-icon>
                        </v-btn>
                        <v-spacer/>
                        <span class="headline">Change Order State</span>
                        <v-spacer/>
                        <v-btn outlined small fab
                               @click="close()">
                            <v-icon dark small>mdi-close</v-icon>
                        </v-btn>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-overflow-btn
                                    v-model="status"
                                    class="my-2"
                                    :items="options"
                                    label="Select state"
                            />

                            <v-textarea
                                    v-if="status == 'REPEAT'"
                                    outlined
                                    name="input-7-4"
                                    label="Describe reason"
                            />
                        </v-container>
                    </v-card-text>
                </form>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    import {mapActions} from "vuex";

    export default {
        props: ['order'],
        data () {
            return {
                dialog: false,
                status: 'DONE',
                options: ['DONE', 'REPEAT'],
            }
        },
        methods: {
            ...mapActions(['fetchOrdersAction']),
            create(){
                axios.post('/graphql', {
                    query:
                        `
                        mutation storeOrder {
                            updateStatusOrder(
                                id: ${this.order.id},
                                orderStatus: "${this.status}",

                            ){
                               id, orderStatus
                            }
                        }
                        `
                })
                .then(res => {
                    this.fetchOrdersAction()
                    this.dialog = false
                })
            },
            close(){
                this.dialog = false
            },
        }
    }
</script>