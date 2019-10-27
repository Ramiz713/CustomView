package com.task.customview.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.customview.ITEM_VIEW_TYPE_DATE_HEADER
import com.task.customview.ITEM_VIEW_TYPE_ITEM
import com.task.customview.ITEM_VIEW_TYPE_SALARY_NOTIFICATION
import com.task.customview.home.entity.Payment

class PaymentAdapter : RecyclerView.Adapter<PaymentDataItemHolder>() {

    private var paymentsDataItems: MutableList<PaymentDataItem> = mutableListOf()

    @Suppress("CheckResult")
    fun submitList(list: List<PaymentDataItem>) =
        paymentsDataItems.addAll(list)

    override fun getItemViewType(position: Int): Int =
        when (paymentsDataItems[position]) {
            is PaymentDataItem.PaymentItem -> ITEM_VIEW_TYPE_ITEM
            is PaymentDataItem.DataHeader -> ITEM_VIEW_TYPE_DATE_HEADER
            is PaymentDataItem.SalaryNotification -> ITEM_VIEW_TYPE_SALARY_NOTIFICATION
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentDataItemHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> PaymentDataItemHolder.PaymentItemHolder.from(
                parent
            )
            ITEM_VIEW_TYPE_DATE_HEADER -> PaymentDataItemHolder.DataHeaderHolder.from(
                parent
            )
            ITEM_VIEW_TYPE_SALARY_NOTIFICATION -> PaymentDataItemHolder.SalaryNotificationHolder.from(
                parent
            )
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    override fun getItemCount(): Int = paymentsDataItems.count()

    override fun onBindViewHolder(holder: PaymentDataItemHolder, position: Int) =
        when (holder) {
            is PaymentDataItemHolder.PaymentItemHolder -> {
                val item = paymentsDataItems[position] as PaymentDataItem.PaymentItem
                holder.bind(item.payment)
            }
            is PaymentDataItemHolder.DataHeaderHolder -> {
                val item = paymentsDataItems[position] as PaymentDataItem.DataHeader
                holder.bind(item.date)
            }
            is PaymentDataItemHolder.SalaryNotificationHolder -> {
                val item = paymentsDataItems[position] as PaymentDataItem.SalaryNotification
                holder.bind(item.sum)
            }
        }

}

sealed class PaymentDataItem {
    data class PaymentItem(val payment: Payment) : PaymentDataItem()
    data class DataHeader(val date: String) : PaymentDataItem()
    data class SalaryNotification(val sum: Double) : PaymentDataItem()
}
