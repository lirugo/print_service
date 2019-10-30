<template>
    <div>
        <v-row>
            <v-col>
                <div class="text-center">
                    <v-pagination
                            v-model="page"
                            :length="12"
                    ></v-pagination>
                </div>
            </v-col>
        </v-row>
        <v-row
                cols="12"
        >

            <v-col
                    v-for="i in 12"
                    :key="i"
                    sm="4"
                    md="3"
            >

                <v-card
                        outlined
                >
                    <span style="position: relative; float: right" class="text-right right ma-2">#001</span>
                    <v-list-item>
                        <v-list-item-avatar color="grey" size="50"></v-list-item-avatar>
                        <v-list-item-content>
                            <v-list-item-title class="headline">John Doe</v-list-item-title>
                            <v-list-item-subtitle>Room 000</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-list-item three-line>
                        <v-list-item-content>
                            <div class="overline">12.12.19 14:30 - Date of request</div>
                            <div class="overline">13.12.19 18:20 - Date of manufacturing</div>
                            <v-list-item-title class="headline mb-1">Name of material</v-list-item-title>
                            <v-list-item-subtitle>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Accusantium aliquid aspernatur debitis dolore ducimus enim labore mollitia, quasi quibusdam voluptatem? Aliquid, odit.</v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>

                    <v-card-actions>
                        <v-btn text icon>
                            <v-icon>mdi-download</v-icon>
                        </v-btn>
                        <v-spacer/>
                        <v-chip color="green">DONE</v-chip>
                    </v-card-actions>
                </v-card>

            </v-col>
        </v-row>
    </div>
</template>

<script>
    export default {
        data: () => ({
            page:1
        })
    }
</script>