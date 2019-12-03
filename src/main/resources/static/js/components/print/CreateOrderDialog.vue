<template>
    <v-dialog v-model="createPrintOrderDialog" persistent max-width="800px">
        <v-card>
            <ValidationObserver v-slot="{ invalid }">
                <form>
                    <v-system-bar lights-out>* All fields is required</v-system-bar>
                    <v-card-title>
                        <v-btn outlined small fab
                               :disabled="invalid"
                               color="green"
                               @click="create()">
                            <v-icon dark small>mdi-content-save</v-icon>
                        </v-btn>
                        <v-spacer/>
                        <span class="headline">Create print order</span>
                        <v-spacer/>
                        <v-btn outlined small fab
                               @click="close()">
                            <v-icon dark small>mdi-close</v-icon>
                        </v-btn>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12" class="pt-0 pb-0">
                                    <v-row cols="12" class="pt-0 pb-0">
                                        <v-col cols="12" sm="12" md="6" class="pt-0 pb-0">
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
                                                            :value="formattedDate"
                                                            label="Select date of manufacturing"
                                                            prepend-icon="mdi-calendar-range"
                                                            readonly
                                                            v-on="on"
                                                    />
                                                </template>
                                                <v-date-picker
                                                        v-model="order.date" @input="showDatePicker = false"/>
                                            </v-menu>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="6" class="pt-0 pb-0">
                                            <v-menu
                                                    ref="menu"
                                                    v-model="showTimePicker"
                                                    :close-on-content-click="false"
                                                    :nudge-right="40"
                                                    :return-value.sync="order.time"
                                                    transition="scale-transition"
                                                    offset-y
                                                    max-width="290px"
                                                    min-width="290px"
                                            >
                                                <template v-slot:activator="{ on }">
                                                    <v-text-field
                                                            v-model="order.time"
                                                            label="Select time of manufacturing"
                                                            prepend-icon="mdi-alarm"
                                                            readonly
                                                            v-on="on"
                                                    />
                                                </template>
                                                <v-time-picker
                                                        v-if="showTimePicker"
                                                        v-model="order.time"
                                                        full-width
                                                        format="24hr"
                                                        @click:minute="$refs.menu.save(order.time)"
                                                />
                                            </v-menu>
                                        </v-col>
                                    </v-row>
                                    <v-row cols="12" class="pt-0 pb-0">
                                        <v-col sm="12" md="6">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-text-field label="Name"
                                                              required
                                                              v-model="order.name"
                                                              :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col sm="12" md="6">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-text-field label="Group name"
                                                              required
                                                              v-model="order.groupName"
                                                              :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col sm="12">
                                            <v-textarea
                                                    outlined
                                                    label="Description"
                                                    v-model="order.description"
                                            />
                                        </v-col>
                                    </v-row>
                                    <v-row cols="12" class="pt-0 pb-0">
                                        <v-col cols="12" sm="12" md="4" class="pt-0 pb-0">
                                            <v-file-input label="File only *.pdf"/>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="4" class="pt-0 pb-0">
                                            <validation-provider name="copies" rules="required|positive" v-slot="{errors}">
                                                <v-text-field
                                                        v-model="order.copies"
                                                        type="number"
                                                        label="Count of copies"
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="4" class="pt-0 pb-0">
                                            <validation-provider name="pages" rules="required|positive" v-slot="{errors}">
                                                <v-text-field
                                                        v-model="order.pages"
                                                        type="number"
                                                        label="Count of pages"
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                    </v-row>

                                    <v-row cols="12" class="pt-0 pb-0">
                                        <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-select
                                                        v-model="order.paperType"
                                                        :items="['A4', 'A5', 'A3']"
                                                        label="Paper type"
                                                        required
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-select
                                                        v-model="order.printType"
                                                        :items="['One_side', 'Two_side']"
                                                        label="Print type"
                                                        required
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-select
                                                        v-model="order.colorType"
                                                        :items="['Black_White', 'Color']"
                                                        label="Print color"
                                                        required
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                        <v-col cols="12" sm="12" md="3" class="pt-0 pb-0">
                                            <validation-provider rules="required" v-slot="{errors}">
                                                <v-select
                                                        v-model="order.orderPriority"
                                                        :items="['Low', 'Medium', 'High']"
                                                        label="Order Priority"
                                                        required
                                                        :error-messages="errors"
                                                />
                                            </validation-provider>
                                        </v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                </form>
            </ValidationObserver>
        </v-card>
    </v-dialog>
</template>

<script>
    import moment from 'moment'
    import axios from "axios";
    import {extend, ValidationObserver, ValidationProvider} from 'vee-validate'
    import {email, required} from 'vee-validate/dist/rules'

    extend('email', email)

    extend('required', {
        ...required,
        message: 'This field is required'
    });

    extend('positive', value => {
        if(value >= 0)
            return true;

        return 'Count of {_field_} must be more than 0';
    })

    export default {
        components: {
            ValidationObserver,
            ValidationProvider,
        },
        props: ['createPrintOrderDialog'],
        data: () => ({
            showDatePicker: false,
            showTimePicker: false,
            order: {
                date: new Date().toISOString().substr(0, 10),
                time: '09:30',
                name: null,
                groupName: null,
                description: null,
                fileName: null,
                copies: null,
                pages: null,
                paperType: null,
                printType: null,
                colorType: null,
                orderPriority: null,
            },
        }),
        computed: {
            formattedDate () {
                return this.order.date ? moment(this.order.date).format('DD-MM-YYYY') : ''
            },
        },
        methods: {
            create(){
                axios.post("/graphql", {
                    query:
                        `mutation storeOrder {` +
                        `    storeOrder(` +
                        `        name: "${this.order.name}",` +
                        `        groupName: "${this.order.groupName}",` +
                        `        description: "${this.order.description}",` +
                        `        fileName: "${this.order.fileName}",` +
                        `        copies: ${this.order.copies},` +
                        `        pages: ${this.order.pages},` +
                        `        orderPriority: "${this.order.orderPriority}",` +
                        `        printType: "${this.order.printType}",` +
                        `        paperType: "${this.order.paperType}",` +
                        `        colorType: "${this.order.colorType}",` +
                        `        manufactureDate: "${this.formattedDate} ${this.order.time}:00"` +
                        `    ){` +
                        `        name, groupName, description, fileName,` +
                        `        copies, pages, orderPriority,` +
                        `        printType, paperType, colorType, manufactureDate` +
                        `    }` +
                        `}`
                })
                    .then(res => {
                        this.$emit('closePrintOrderDialog')
                    })

            },
            close(){
                this.$emit('closePrintOrderDialog')
            },
        },
    }
</script>