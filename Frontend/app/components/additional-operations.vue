<script setup lang="ts">
import xml2js from "xml2js";

function parseXml(xmlText: string) {
  return xml2js.parseStringPromise(xmlText, {
    explicitArray: false
  });
}

const BASE_URL = 'http://localhost:8080/api/v1'

const sum = ref<number>();

async function onDistanceSumClick() {
  const resp = await $fetch(`${BASE_URL}/routes/distance/sum`, {
    method: 'get'
  }).catch(err => sum.value = err);
  const obj = await parseXml(resp);
  sum.value = obj.sum;
}

const count = ref<number>();
const inputThreshold = ref<number>();
const threshold = ref<number>();

async function onCountLessClick() {
    threshold.value = inputThreshold.value;
    inputThreshold.value = undefined;
  const resp = await $fetch(`${BASE_URL}/routes/distance/count-less`, {
    method: 'get',
    query: { threshold: threshold.value }
  }).catch(err => count.value = err);
  const obj = await parseXml(resp);
  count.value = obj.count;
}

</script>

<template>

  <div class="p-3">
    <p class="text-lg pb-2">All distance sum: {{ sum }}</p>
    <UButton color="neutral" variant="outline" label="Calculate"
             @click="onDistanceSumClick" loading-auto/>
  </div>

  <div class="p-3">
    <p class="text-lg pb-2">Count of routes with distance less than {{ threshold }}: {{ count }}</p>
    <UInputNumber
        placeholder="threshold" orientation="vertical"
        class="w-30 pb-2 pr-2"
        v-model="inputThreshold"/>
    <UButton color="neutral" variant="outline" label="Calculate"
             @click="onCountLessClick" loading-auto/>
  </div>

</template>
