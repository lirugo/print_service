<template>
    <v-app>

        <toolbar @createPrintOrder="onCreatePrintOrder()"/>
        <v-content>
            <v-container fluid
                         fill-height
            >
                <ListOfOrders/>
            </v-container>
        </v-content>

        <!-- CreatePrintOrder Dialog -->
        <v-dialog v-model="createPrintOrderDialog" persistent max-width="800px">
            <v-card>
                <v-card-title>
                    <span class="headline">Create print order</span>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" class="pt-0 pb-0">
                                <v-row cols="12" class="pt-0 pb-0">
                                    <v-col sm="6" class="pt-0 pb-0">
                                        <v-menu
                                                v-model="showDatePicker"
                                                :close-on-content-click="false"
                                                :nudge-right="40"
                                                transition="scale-transition"
                                                offset-y
                                                min-width="290px"
                                        >
                                            <template v-slot:activator="{ on }">
                                                <v-text-field
                                                        v-model="picker"
                                                        label="Select date of manufacturing"
                                                        prepend-icon="mdi-calendar-range"
                                                        readonly
                                                        v-on="on"
                                                ></v-text-field>
                                            </template>
                                            <v-date-picker v-model="picker" @input="showDatePicker = false"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                    <v-col sm="6" class="pt-0 pb-0">
                                        <v-menu
                                                ref="menu"
                                                v-model="showTimePicker"
                                                :close-on-content-click="false"
                                                :nudge-right="40"
                                                :return-value.sync="time"
                                                transition="scale-transition"
                                                offset-y
                                                max-width="290px"
                                                min-width="290px"
                                        >
                                            <template v-slot:activator="{ on }">
                                                <v-text-field
                                                        v-model="time"
                                                        label="Select time of manufacturing"
                                                        prepend-icon="mdi-alarm"
                                                        readonly
                                                        v-on="on"
                                                ></v-text-field>
                                            </template>
                                            <v-time-picker
                                                    v-if="showTimePicker"
                                                    v-model="time"
                                                    full-width
                                                    format="24hr"
                                                    @click:minute="$refs.menu.save(time)"
                                            ></v-time-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>

                                <v-text-field label="Name" required></v-text-field>
                                <v-textarea
                                        outlined
                                        label="Description"
                                ></v-textarea>

                                <v-file-input label="Upload file"></v-file-input>

                                <v-row cols="12" class="pt-0 pb-0">
                                    <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                        <v-text-field
                                                type="number"
                                                label="Count of copies"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                        <v-text-field
                                                type="number"
                                                label="Count of pages"
                                        ></v-text-field>
                                    </v-col>
                                    <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                        <v-select
                                                :items="['1 sides', '2 sides']"
                                                label="Print type"
                                                required
                                        ></v-select>
                                    </v-col>
                                    <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                        <v-select
                                                :items="['Black/White', 'Color']"
                                                label="Print color"
                                                required
                                        ></v-select>
                                    </v-col>
                                    <v-col class="pt-0 pb-0">

                                        <v-checkbox input-value="true"
                                                    append-icon="mdi-exclamation"
                                                    label="High priority"
                                                    value="false"
                                        ></v-checkbox>
                                    </v-col>
                                </v-row>
                            </v-col>

                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="createPrintOrderDialog = false">Close</v-btn>
                    <v-btn color="blue darken-1" text @click="createPrintOrderDialog = false">CREATE</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>

    </v-app>
</template>

<script>
    import Toolbar from 'components/Toolbar.vue'
    import ListOfOrders from 'components/print/ListOfOrders.vue'

    export default {
        components: {
            Toolbar,
            ListOfOrders,
        },
        data: () => ({
            createPrintOrderDialog: true,
            showDatePicker: false,
            showTimePicker: false,
            picker: new Date().toISOString().substr(0, 10),
            time: '09:30',
        }),
        methods: {
            onCreatePrintOrder(){
                this.createPrintOrderDialog = true
            }
        }
    }
</script>