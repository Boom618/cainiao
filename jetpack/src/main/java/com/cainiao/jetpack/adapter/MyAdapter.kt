package com.cainiao.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.cainiao.jetpack.databinding.ItemRvMainBinding
import com.cainiao.jetpack.db.ItemBean

/**
 * @author boomhe on 2020/9/21.
 */
class MyAdapter(diff: DiffUtil.ItemCallback<ItemBean>) :
    PagedListAdapter<ItemBean, MyAdapter.ListViewHolder>(diff) {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflate = ItemRvMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(inflate)
    }

    class ListViewHolder(private val binding: ItemRvMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemBean?) {
            binding.bean = item
            binding.listener = View.OnClickListener {
                ToastUtils.showShort("点击了 $item")
            }
        }
    }
}