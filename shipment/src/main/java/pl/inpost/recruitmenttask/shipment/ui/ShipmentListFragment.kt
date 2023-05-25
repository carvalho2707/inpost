package pl.inpost.recruitmenttask.shipment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pl.inpost.recruitmenttask.shipment.R
import pl.inpost.recruitmenttask.shipment.databinding.FragmentShipmentListBinding
import pl.inpost.recruitmenttask.shipment.databinding.ShipmentItemBinding
import pl.inpost.recruitmenttask.shipment.presentation.ShipmentListViewModel
import pl.inpost.recruitmenttask.shipment.utils.SpacingItemDecoration

@AndroidEntryPoint
class ShipmentListFragment : Fragment() {

    private val viewModel: ShipmentListViewModel by viewModels()
    private var _binding: FragmentShipmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.shipment_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShipmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shipmentAdapter = ShipmentAdapter(
            onClickMore = {
                //TODO implement navigation to details
            }
        )

        binding.shipmentsRecyclerview.apply {
            adapter = shipmentAdapter
            addItemDecoration(
                SpacingItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.shipment_item_spacing)
                )
            )
            layoutManager = LinearLayoutManager(context)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.viewState.observe(requireActivity()) { shipments ->
            shipmentAdapter.submitList(shipments)
        }
    }

    override fun onDestroyView() {
        binding.shipmentsRecyclerview.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = ShipmentListFragment()
    }
}
