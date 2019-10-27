package com.task.customview

import com.task.customview.home.adapter.PaymentDataItem
import com.task.customview.home.entity.Payment
import com.task.customview.home.entity.PaymentOperation
import com.task.customview.home.entity.Section
import com.task.customview.samokatus.entity.Filter
import com.task.customview.samokatus.entity.Tour

class MockedRepository {

    private val paymentOperations: List<Section> = listOf(
        Section(
            listOf(
                Payment(
                    1,
                    "Зарплата",
                    72,
                    PaymentOperation.INCOME,
                    22578.81,
                    "30.09.2019"
                ),
                Payment(
                    2,
                    "Вывод денег",
                    72,
                    PaymentOperation.OUTCOME,
                    1050.0,
                    "30.09.2019"
                )
            ),
            "Вчера"
        ),
        Section(
            listOf(
                Payment(
                    3,
                    "Вывод денег в две строки",
                    72,
                    PaymentOperation.OUTCOME,
                    16085.75,
                    "29.09.2019"
                ),
                Payment(
                    4,
                    "Вывод денег",
                    72,
                    PaymentOperation.OUTCOME,
                    1750.0,
                    "29.09.2019"
                ),
                Payment(
                    5,
                    "Бонусы",
                    72,
                    PaymentOperation.INCOME,
                    54.10,
                    "29.09.2019"
                )
            ),
            "29 сентября, пн"
        ),
        Section(
            listOf(
                Payment(
                    6,
                    "Аванс",
                    72,
                    PaymentOperation.INCOME,
                    20000.00,
                    "25.09.2019"
                )
            ),
            "25 сентября, пн"
        )
    )

    private val filterItems = listOf(
        Filter(
            1,
            "Подписки",
            R.drawable.ic_subscription,
            R.drawable.bg_subscription
        ),
        Filter(
            2,
            "Авиабилеты",
            R.drawable.ic_airplane_white,
            R.drawable.bg_airplane
        ),
        Filter(
            3,
            "Туры",
            R.drawable.ic_travel_white,
            R.drawable.bg_tours
        ),
        Filter(
            4,
            "Отели",
            R.drawable.ic_hotels,
            R.drawable.bg_hotels
        )
    )

    private val tourItems = listOf(
        Tour(
            1,
            "Круиз с безвиззовой Англией – 259€",
            "7 дней в апреле 2019 года - vandrouki",
            R.drawable.ic_airplane
        ),
        Tour(
            2,
            "Из Москвы в Пекин \uD83C\uDDE8\uD83C\uDDF3 за 16 800 р. RT (июнь)",
            "MIAT, по пути можно посмотреть столицу Монголии",
            R.drawable.ic_cruise
        ),
        Tour(
            3,
            "Тур из Москвы в Тунис \uD83C\uDDF9\uD83C\uDDF3 – 15 400 р./чел",
            "Вылет 4 мая на 7 ночей",
            R.drawable.ic_travel
        ),
        Tour(
            4,
            "Тур из Москвы на Мальорку \uD83C\uDDEA\uD83C\uDDF8 – 20 500 р./чел.",
            "Вылет 29 мая на 3 ночи",
            R.drawable.ic_travel
        ),
        Tour(
            5,
            "Тур из Москвы в Тунис \uD83C\uDDF9\uD83C\uDDF3 – 15 400 р./чел.",
            "Вылет 4 мая на 7 ночей",
            R.drawable.ic_fire
        )

    )

    fun getPaymentItems(): List<PaymentDataItem> =
        listOf(PaymentDataItem.SalaryNotification(30000.0)) +
            paymentOperations.flatMap {
                listOf(PaymentDataItem.DataHeader(it.date)) +
                    it.payments.map { item -> PaymentDataItem.PaymentItem(item) }
            }

    fun getFilterItems(): List<Filter> = filterItems

    fun getTourItems(): List<Tour> = tourItems

}

