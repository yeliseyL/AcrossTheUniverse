package com.eliseylobanov.acrosstheuniverse.ui.notes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.eliseylobanov.acrosstheuniverse.R
import com.eliseylobanov.acrosstheuniverse.databinding.NotesFragmentBinding

class NotesFragment : Fragment(R.layout.notes_fragment) {

    private val viewModel: NotesViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            NotesViewModel.Factory(activity.application)
        ).get(NotesViewModel::class.java)
    }

    lateinit var binding: NotesFragmentBinding
    lateinit var adapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = NotesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = NotesAdapter(NotesAdapter.OnClickListener {
            viewModel.displayNote(it)
        }, viewModel)

        binding.recyclerNotes.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerNotes)

        viewModel.navigateToSelectedNote.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController()
                    .navigate(NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(it))
                viewModel.displayNoteComplete()
            }
        })

        binding.fab.setOnClickListener {
            findNavController()
                .navigate(NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(null))
        }

        return binding.root
    }
}