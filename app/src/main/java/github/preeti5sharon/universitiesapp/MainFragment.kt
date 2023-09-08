package github.preeti5sharon.universitiesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import github.preeti5sharon.universitiesapp.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), onClickListener {
    private val viewModel: UniversitiesViewModel by viewModels()
    private var binding: FragmentMainBinding? = null
    private val adapter = RecyclerAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.getUniversitiesList()
            binding?.swipeRefresh?.isRefreshing = false
        }
        lifecycleScope.launch {
            viewModel.universitiesList.collectLatest {
                adapter.asyncDiffer.submitList(it)
            }
        }
        binding?.recyclerView?.adapter = adapter
    }

    override fun onClickListener(position: Int, name: UniversityResponseItem?) {
        Toast.makeText(activity, "$position ${name?.name}", Toast.LENGTH_SHORT).show()
    }
}
